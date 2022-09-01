package com.infoweaver.springtutorial.controller;

/**
 * @author Ruobing Shang 2022-08-31
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String greeting() {
        return "Hello Spring, I'm Ruobing Shang.";
    }
}