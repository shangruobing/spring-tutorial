package com.infoweaver.springtutorial.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-01
 */

@RestController
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @JsonView(User.WithoutPassword.class)
    public List<User> selectAllUser() {
        return userService.listUsers();
    }

    @GetMapping("/user/{id}")
    @JsonView(User.WithoutPassword.class)
    public User getUserById(@PathVariable("id") String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public int add(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.saveUser(user);
    }

    @PutMapping("/user")
    public int update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public int delete(@PathVariable("id") String id) {
        return userService.removeUser(id);
    }

    @Deprecated
    @PostMapping("/user/old-login")
    public Map<String, String> oldLogin(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.login(user.getName(), user.getPassword());
    }

}