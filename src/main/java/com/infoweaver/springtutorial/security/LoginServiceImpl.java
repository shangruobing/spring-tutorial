package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.util.JwtAuthentication;
import com.infoweaver.springtutorial.util.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-28 8:19
 * TODO: need a ILoginService
 */

@Service
public class LoginServiceImpl {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Map<String, String> login(User user) throws NoSuchAlgorithmException {
        System.out.println("user name" + user.getName());
        System.out.println("user password" + user.getPassword());
        System.out.println("user encryption" + KeyUtils.encryption(user.getPassword()));
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        System.out.println("token " + token);
        Authentication authentication = authenticationManager.authenticate(token);
        System.out.println("authentication" + authentication);
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println("user" + loginUser);
        return JwtAuthentication.createToken(loginUser.getUser().getId(), loginUser.getUsername());
    }

    public Map<String, String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getUser().getId();
        return Map.of(userid, "Logout");
    }
}