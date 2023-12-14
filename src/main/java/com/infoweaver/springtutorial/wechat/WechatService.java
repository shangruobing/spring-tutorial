package com.infoweaver.springtutorial.wechat;

import com.infoweaver.springtutorial.wechat.entity.AccessToken;
import com.infoweaver.springtutorial.wechat.entity.AuthorizeResponse;
import com.infoweaver.springtutorial.wechat.entity.BindResponse;
import com.infoweaver.springtutorial.wechat.entity.TemplateMessageResponse;

/**
 * @author Ruobing Shang 2023-10-28 20:07
 */
public interface WechatService {
    /**
     * 前端获取关注公众号的微信用户openid及unionid流程
     * 前置流程
     * 1、用户扫码关注微信公众号，此时用户的openid和unionid已经存入微信公众号平台
     * 前端工作一阶段
     * 2、前端通过让用户点击链接https://open.weixin.qq.com/connect/oauth2/authorize得到用户code
     * 3、前端将code发送至后端服务器GET 微信用户信息接口
     * 后端工作
     * 4、后端将appid、secret、code发送至微信服务器https://api.weixin.qq.com/sns/oauth2/access_token得到access token
     * 5、后端得到access token发送请求至微信服务器https://api.weixin.qq.com/sns/userinfo得到用户的unionid、openid返回前端
     * 前端工作二阶段
     * 6、前端将unionid、openid、用户信息通过调用后端PUT User接口进行绑定
     */
    /**
     * 获取微信公众号的access-token
     *
     * @issue 应该存入redis且不返回前端
     */
    AccessToken getWechatOfficialAccountAccessToken();

    /**
     * 发送模板消息给指定用户
     */
    TemplateMessageResponse sendTemplateMessage();

    /**
     * 获取用户授权信息
     */
    AuthorizeResponse getWechatAuthorizeInfo(String code);

    /**
     * 微信用户授权
     *
     * @param code  微信的验证码code
     * @param state 后端的access token
     */
    BindResponse bindWechatAuthorize(String code, String state);
}
