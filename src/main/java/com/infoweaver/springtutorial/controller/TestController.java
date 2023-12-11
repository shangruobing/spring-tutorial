package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.annotation.Debounce;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruobing Shang 2023-09-26 08:35
 */
@RestController
public class TestController {
    @Debounce(5000)
    @GetMapping("/ping")
    public String testConnection() {
        return "Backend Connection is OK!";
    }
}
