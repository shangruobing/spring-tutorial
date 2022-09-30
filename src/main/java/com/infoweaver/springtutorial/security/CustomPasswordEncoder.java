package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.util.KeyUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;

/**
 * @author Ruobing Shang 2022-09-28 11:00
 */

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        System.out.println("运行了encode");
        System.out.println("raw password" + rawPassword);
        try {
            return KeyUtils.encryption(rawPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            System.out.println("encodedPassword是" + encodedPassword);
            System.out.println("char密码是" + rawPassword);
            System.out.println(encodedPassword.equals(KeyUtils.encryption(rawPassword.toString())));
            return encodedPassword.equals(KeyUtils.encryption(rawPassword.toString()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}