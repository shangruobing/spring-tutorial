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
public class TemplateMessageResponse {
    private Long msgid;
    private String errcode;
    private String errmsg;
}
