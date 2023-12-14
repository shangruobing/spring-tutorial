package com.infoweaver.springtutorial.wechat;

import com.infoweaver.springtutorial.wechat.entity.AccessToken;
import com.infoweaver.springtutorial.wechat.entity.AuthorizeResponse;
import com.infoweaver.springtutorial.wechat.entity.BindResponse;
import com.infoweaver.springtutorial.wechat.entity.TemplateMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ruobing Shang 2023-10-28 20:24
 */
@RestController
public class WechatController {
    private final WechatServiceImpl wechatService;

    @Autowired
    public WechatController(WechatServiceImpl wechatService) {
        this.wechatService = wechatService;
    }

    /**
     * 该接口不应该给前端
     */
    @GetMapping("/wechat-official-account/access-token")
    public AccessToken getWechatOfficialAccountAccessToken() {
        return wechatService.getWechatOfficialAccountAccessToken();
    }

    /**
     * 该接口不应该给前端
     */
    @PostMapping("/wechat-official-account/send-template-message")
    public TemplateMessageResponse sendTemplateMessage() {
        return wechatService.sendTemplateMessage();
    }

    /**
     * 绑定微信授权
     *
     * @param code  微信的验证码code
     * @param state 后端的access token
     */
    @GetMapping("/wechat-authorize")
    public ModelAndView bindWechatAuthorize(@RequestParam String code, @RequestParam String state) {
        BindResponse bindResponse = wechatService.bindWechatAuthorize(code, state);
        return new ModelAndView("message")
                .addObject("message", bindResponse.getMessage())
                .addObject("state", bindResponse.getState());
    }

    /**
     * 该接口不应该给前端
     */
    @GetMapping("/wechat-authorize-info")
    public AuthorizeResponse getWechatAuthorizeInfo(@RequestParam String code) {
        return wechatService.getWechatAuthorizeInfo(code);
    }
}
