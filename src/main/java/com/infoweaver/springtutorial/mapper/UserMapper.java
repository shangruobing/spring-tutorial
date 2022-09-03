package com.infoweaver.springtutorial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.infoweaver.springtutorial.entity.User;
import org.apache.ibatis.annotations.Mapper;
/**
 * @author Ruobing Shang 2022-09-01
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
