package com.infoweaver.springtutorial.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Ruobing Shang 2023-09-28 17:19
 */
@Data
public class AuthenticationDto {
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
}
