package com.infoweaver.springtutorial.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ruobing Shang 2022-09-27 21:59
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getName, username);
        User user = Optional.ofNullable(userMapper.selectOne(wrapper)).orElseThrow(() ->
                new UsernameNotFoundException(username + " NOT_FOUND")
        );
        System.out.println("loadUserByUsername" + user);
        return new LoginUser(user);
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userMapper.selectById(id)).orElseThrow(() ->
                new UsernameNotFoundException(id + " NOT_FOUND")
        );
        return new LoginUser(user);
    }
}
