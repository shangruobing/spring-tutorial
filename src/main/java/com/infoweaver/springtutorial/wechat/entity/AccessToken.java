package com.infoweaver.springtutorial.wechat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Ruobing Shang 2023-10-28 20:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AccessToken {
    private String access_token;
    private Integer expires_in;
    private String errcode;
    private String errmsg;
}
