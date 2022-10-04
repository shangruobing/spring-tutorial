package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@TableName(value = "tb_user")
public class User extends Model<User> {
    public interface WithoutPassword {
    }
    @JsonView(WithoutPassword.class)
    private String id;
    @JsonView(WithoutPassword.class)
    private String name;
    @JsonView(WithoutPassword.class)
    private Integer age;
    @JsonView(WithoutPassword.class)
    private String email;
    /**
     * use @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     * lead to security authenticate fail.
     * use @JsonView could exclude password filed to frontend.
     */
    private String password;
}
