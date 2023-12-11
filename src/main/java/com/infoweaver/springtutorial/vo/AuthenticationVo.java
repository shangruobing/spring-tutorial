package com.infoweaver.springtutorial.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author Ruobing Shang 2023-10-13 16:15
 */
@Data
@Accessors(chain = true)
public class AuthenticationVo {
    /**
     * 认证信息
     */
    private Map<String, String> auth;
    /**
     * 外键用户
     */
    private UserVo user;
}
