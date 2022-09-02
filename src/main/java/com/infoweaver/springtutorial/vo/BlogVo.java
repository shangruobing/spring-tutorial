package com.infoweaver.springtutorial.vo;

/**
 * @author Ruobing Shang 2022-09-01 20:53
 */

import com.infoweaver.springtutorial.entity.Blog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BlogVo extends Blog {
    private Long userId;

    public BlogVo(Blog blog) {
        super(blog);
    }
}