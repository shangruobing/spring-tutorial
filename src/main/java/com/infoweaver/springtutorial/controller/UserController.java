package com.infoweaver.springtutorial.controller;

import java.util.List;

import com.infoweaver.springtutorial.entity.User;
import com.infoweaver.springtutorial.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<User> selectAllUser() {
        return userService.listUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public int add(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PutMapping("/user")
    public int update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public int delete(@PathVariable("id") long id) {
        return userService.removeUser(id);
    }

}


