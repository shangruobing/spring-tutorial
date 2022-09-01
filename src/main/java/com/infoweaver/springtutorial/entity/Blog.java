package com.infoweaver.springtutorial.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-01 20:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class Blog extends Model<Blog> {
    private Long id;
    private String title;
    private Long userId;


    public Blog(Blog dept) {
        Optional.ofNullable(dept).ifPresent(e -> {
            this.id = e.getId();
            this.title = e.getTitle();
            this.userId = e.getUserId();
        });
    }
}
