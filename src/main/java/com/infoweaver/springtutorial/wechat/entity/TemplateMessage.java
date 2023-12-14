package com.infoweaver.springtutorial.wechat.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author Ruobing Shang 2023-10-28 20:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TemplateMessage {
    private String template_id;
    private String touser;
    private Map<String, Map<String,String>> data;
}
