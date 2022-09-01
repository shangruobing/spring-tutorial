package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    public User(User user) {
        if (user != null) {
            this.id = user.getId();
            this.name = user.getName();
            this.age = user.getAge();
            this.email = user.getEmail();
        }
    }
}
