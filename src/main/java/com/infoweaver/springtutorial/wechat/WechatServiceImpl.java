package com.infoweaver.springtutorial.wechat;

import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.service.impl.UserServiceImpl;
import com.infoweaver.springtutorial.util.JwtAuthenticationUtils;
import com.infoweaver.springtutorial.util.RedisUtils;
import com.infoweaver.springtutorial.wechat.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.security.sasl.AuthenticationException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ruobing Shang 2023-10-28 20:07
 */
@Service
public class WechatServiceImpl implements WechatService {
    private static final String APPID = "wxa6909358cbfd8e4d";
    private static final String SECRET = "13e24a162515038d3f3f73ba645ee089";
    private final RestTemplate restTemplate;
    private final UserServiceImpl userService;
    private final RedisUtils redisUtils;

    @Autowired
    public WechatServiceImpl(RestTemplate restTemplate, UserServiceImpl userService, RedisUtils redisUtils) {
        this.restTemplate = restTemplate;
        this.userService = userService;
        this.redisUtils = redisUtils;
    }

    /**
     * 获取微信公众号的access-token
     *
     * @issue 应该存入redis且不返回前端
     */
    @Override
    public AccessToken getWechatOfficialAccountAccessToken() {
        return (AccessToken) Optional.ofNullable(redisUtils.get("wechat-official-account-access-token")).orElseGet(() -> {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
            AccessToken accessToken = restTemplate.getForObject(url, AccessToken.class);
            if (accessToken != null) {
                redisUtils.set("wechat-official-account-access-token", accessToken, Duration.ofHours(1));
            }
            return accessToken;
        });
    }

    /**
     * 发送模板消息给指定用户
     */
    @Override
    public TemplateMessageResponse sendTemplateMessage() {
        String accessToken = getWechatOfficialAccountAccessToken().getAccess_token();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        Map<String, Map<String, String>> data = new HashMap<>();
        data.put("thing01", Map.of("value", "若水"));
        data.put("phone_number01", Map.of("value", "13577070376"));
        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTemplate_id("OPCOz6b4MriYW6pSsf56xGCa6mVklJiIFmnRxH_exlM")
                .setTouser("oyvO-6WycZzUAzC1TEUqHcU1peNs")
                .setData(data);
        return restTemplate.postForEntity(url, templateMessage, TemplateMessageResponse.class).getBody();
    }

    /**
     * 获取用户授权信息
     */
    @Override
    public AuthorizeResponse getWechatAuthorizeInfo(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret=" + SECRET + "&code=" + code + "&grant_type=authorization_code";
        return restTemplate.getForObject(url, AuthorizeResponse.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BindResponse bindWechatAuthorize(String code, String state) {
        try {
            /**
             * 根据access_token解析用户id
             */
            Integer userId = Integer.valueOf(JwtAuthenticationUtils.parseAuth(state).getId());
            Optional<User> optionalUser = Optional.ofNullable(userService.getById(userId));
            if (optionalUser.isEmpty()) {
                return new BindResponse(false, "绑定失败，用户不存在");
            }
            User user = optionalUser.get();
            /**
             * 如果用户的openid为空
             */
            if (user.getOpenId().isEmpty()) {
                /**
                 * 根据微信链接的验证码code获取用户的openid
                 */
                Optional<String> optionalOpenid = Optional.ofNullable(getWechatAuthorizeInfo(code).getOpenid());
                if (optionalOpenid.isEmpty()) {
                    return new BindResponse(false, "绑定失败，链接已过期请重新获取");
                }
                user.setOpenId(optionalOpenid.get());
                userService.updateById(user);
                String message = "用户：" + user.getName() + " 手机号：" + user.getPhone() + " 绑定成功！";
                return new BindResponse(true, message);
            } else {
                return new BindResponse(false, "绑定失败，该用户已经绑定过微信");
            }
        } catch (AuthenticationException e) {
            return new BindResponse(false, "绑定失败，链接已过期请重新获取");
        }
    }
}
