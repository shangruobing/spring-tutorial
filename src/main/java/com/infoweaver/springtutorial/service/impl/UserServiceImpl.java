package com.infoweaver.springtutorial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.mapper.UserMapper;
import com.infoweaver.springtutorial.service.IUserService;
import com.infoweaver.springtutorial.util.JwtAuthentication;
import com.infoweaver.springtutorial.util.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-01
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> listUsers() {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public int saveUser(User user) throws NoSuchAlgorithmException {
        user.setPassword(KeyGenerator.encryption(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int removeUser(String id) {
        return userMapper.deleteById(id);
    }

    @Override
    public Map<String, String> login(String username, String password) throws NoSuchAlgorithmException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class);
        wrapper.eq(User::getName, username).eq(User::getPassword, KeyGenerator.encryption(password));
        User user = userMapper.selectOne(wrapper);
        return JwtAuthentication.createToken(user.getId(), username);
    }
}
