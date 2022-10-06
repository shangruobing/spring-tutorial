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
        try {
            return KeyUtils.encryption(rawPassword.toString());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return encodedPassword.equals(KeyUtils.encryption(rawPassword.toString()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}