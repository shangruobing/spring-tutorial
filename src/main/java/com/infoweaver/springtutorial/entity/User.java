package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author Ruobing Shang 2023-10-12 18:18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User extends BaseEntity {
    /**
     * 微信unionId
     */
    protected String unionId;
    /**
     * 微信公众号openId
     */
    protected String openId;
    /**
     * 角色
     */
    protected String role;
    /**
     * 姓名
     */
    protected String name;
    /**
     * 是否启用
     */
    protected Boolean state;
    /**
     * 用户名称
     */
    protected String username;
    /**
     * 性别
     */
    protected String gender;
    /**
     * 手机号 需要唯一
     */
    protected String phone;
    /**
     * 邮箱号
     */
    protected String email;
    /**
     * 创建时间
     */
    protected LocalDateTime joinTime;
    /**
     * 上次登录时间 需要维护
     */
    protected LocalDateTime lastLoginTime;
    /**
     * 公司ID
     */
    protected Integer companyId;
    /**
     * 密码字段在Vo处需要加密
     */
    protected String password;
}
