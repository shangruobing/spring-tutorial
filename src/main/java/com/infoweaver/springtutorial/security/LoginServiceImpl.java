package com.infoweaver.springtutorial.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.service.impl.UserServiceImpl;
import com.infoweaver.springtutorial.util.JwtAuthenticationUtils;
import com.infoweaver.springtutorial.util.KeyUtils;
import com.infoweaver.springtutorial.util.RedisUtils;
import com.infoweaver.springtutorial.vo.AuthenticationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ruobing Shang 2023-10-24 08:33
 */
@Service
public class LoginServiceImpl {
    private final UserServiceImpl userService;
    private final RedisUtils redisUtils;

    @Autowired
    public LoginServiceImpl(UserServiceImpl userService, RedisUtils redisUtils) {
        this.userService = userService;
        this.redisUtils = redisUtils;
    }

    public AuthenticationVo login(String phone, String password) {
        /**
         * 先判断手机号是否存在
         */
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getPhone, phone);
        User user = Optional.ofNullable(userService.getOne(wrapper)).orElseThrow(() -> new RuntimeException("该手机号未注册"));
        /**
         * 再判断密码是否正确
         */
        if (user.getPassword().equals(KeyUtils.encryption(password))) {
            /**
             * 更新用户最后登录时间
             */
            user.setLastLoginTime(LocalDateTime.now());
            userService.updateById(user);
            if (!user.getState()) {
                throw new RuntimeException("该用户已被禁用，请联系管理员");
            }
            Map<String, String> auth = JwtAuthenticationUtils.createToken(String.valueOf(user.getId()), user.getPhone());
            String cacheKey = "JWT_LOGIN_USER_" + user.getId();
            redisUtils.set(cacheKey, auth.get("access_token"), Duration.ofDays(7));
            return new AuthenticationVo().setUser(userService.getUserVoById(user.getId())).setAuth(auth);
        } else {
            throw new RuntimeException("手机号或密码错误");
        }
    }
}
