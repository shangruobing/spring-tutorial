package com.infoweaver.springtutorial.dto;

import com.infoweaver.springtutorial.entity.BaseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Ruobing Shang 2023-09-29 21:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto {
    /**
     * 记录Id
     */
    @NotNull(message = "ID不能为空", groups = {ValidatedGroup.Update.class, ValidatedGroup.Delete.class})
    private Integer id;
    /**
     * 微信unionId
     */
    private String unionId;
    /**
     * 微信公众号openId
     */
    private String openId;
    /**
     * 是否停用
     */
    private Boolean state;
    /**
     * 角色
     */
    @NotNull(message = "角色不能为空", groups = {ValidatedGroup.Create.class})
    private String role;
    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = {ValidatedGroup.Create.class})
    private String username;
    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = {ValidatedGroup.Create.class})
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = {ValidatedGroup.Create.class})
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 创建时间
     */
    private LocalDateTime joinTime;
    /**
     * 上次登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     * 公司ID
     */
    @NotNull(message = "公司ID不能为空", groups = {ValidatedGroup.Create.class})
    private Integer companyId;
    /**
     * 密码
     */
    @NotNull(message = "密码不能为空", groups = {ValidatedGroup.Create.class})
    private String password;
}
