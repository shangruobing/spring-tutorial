package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.util.KeyUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ruobing Shang 2023-10-24 08:32
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * 自定义密码加密类
     *
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return KeyUtils.encryption(rawPassword.toString());
    }

    /**
     * 自定义密码匹配类
     *
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(KeyUtils.encryption(rawPassword.toString()));
    }
}
