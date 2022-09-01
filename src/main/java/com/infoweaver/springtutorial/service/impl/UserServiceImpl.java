package com.infoweaver.springtutorial.service.impl;

import java.util.List;

import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.mapper.UserMapper;
import com.infoweaver.springtutorial.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public int editUser(User user) {
        return userMapper.updateById(user);
    }

    public int removeUser(Long id) {
        return userMapper.deleteById(id);
    }
}
