package com.infoweaver.springtutorial.security;

import com.infoweaver.springtutorial.entity.AuthenticationDto;
import com.infoweaver.springtutorial.vo.AuthenticationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruobing Shang 2023-10-24 08:32
 */
@RestController
public class LoginController {
    private final LoginServiceImpl loginService;

    @Autowired
    public LoginController(LoginServiceImpl loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/user/login")
    public AuthenticationVo login(@Validated @RequestBody AuthenticationDto authenticationDto) {
        return loginService.login(authenticationDto.getPhone(), authenticationDto.getPassword());
    }
}
