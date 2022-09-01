package com.infoweaver.springtutorial.entity.vo;

/**
 * @author Ruobing Shang 2022-09-01 20:53
 */

import com.infoweaver.springtutorial.entity.Blog;
import com.infoweaver.springtutorial.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class UserVo extends User {
    private List<Blog> blogs;

    public UserVo(User user) {
        super(user);
    }
}