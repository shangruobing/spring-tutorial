package com.infoweaver.springtutorial.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ruobing Shang 2022-09-28 11:00
 */

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        System.out.println("s是"+s);
        System.out.println("char密码是"+charSequence);
        System.out.println(s.equals(charSequence.toString()));
        return s.equals(charSequence.toString());
    }
}