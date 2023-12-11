package com.infoweaver.springtutorial.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.infoweaver.springtutorial.annotation.Debounce;
import com.infoweaver.springtutorial.dto.UserDto;
import com.infoweaver.springtutorial.dto.ValidatedGroup;
import com.infoweaver.springtutorial.po.ExcelPrecheckResult;
import com.infoweaver.springtutorial.service.impl.UserServiceImpl;
import com.infoweaver.springtutorial.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Ruobing Shang 2023-09-20 12:18
 */
@RestController
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public IPage<UserVo> selectUser(@Validated @RequestBody UserDto userDto) {
        return userService.listUsers(userDto);
    }

    @GetMapping("/user/{id}")
    public UserVo getUser(@PathVariable Integer id) {
        return userService.getUserVoById(id);
    }

    @Debounce(1000)
    @PostMapping("/user/add")
    public UserVo addUser(@Validated({ValidatedGroup.Create.class}) @RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @Debounce(1000)
    @PutMapping("/user")
    public UserVo updateUser(@Validated({ValidatedGroup.Update.class}) @RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @Debounce(1000)
    @PostMapping("user/upload/precheck")
    public ResponseEntity<ExcelPrecheckResult> precheckUploadUsers(@RequestParam(value = "file") MultipartFile file) {
        return userService.precheckUploadUser(file);
    }

    @Debounce(1000)
    @PostMapping("user/upload/batch-insert")
    public String batchInsertUserWithExcel(@RequestParam(value = "file") MultipartFile file) {
        return userService.batchInsertUserWithExcel(file);
    }

    @Debounce(1000)
    @GetMapping("user/export")
    public ResponseEntity<?> exportUser() {
        return userService.exportUser();
    }

}
