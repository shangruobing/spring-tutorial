package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.entity.Blog;
import com.infoweaver.springtutorial.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("${sqlStr}")
    List<Blog> dynamicSql(@Param("sqlStr")String sql);
}
