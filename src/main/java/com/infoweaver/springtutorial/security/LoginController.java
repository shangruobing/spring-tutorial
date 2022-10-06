package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-28 8:39
 */
@RestController
public class LoginController {
    private final LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/user/login")
    public Map<String, String> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public Map<String, String> logout() {
        return loginService.logout();
    }

    @GetMapping("/user/admin")
    public String testAdminPermission() {
        return "You need Admin permission to retrieve it";
    }
}
