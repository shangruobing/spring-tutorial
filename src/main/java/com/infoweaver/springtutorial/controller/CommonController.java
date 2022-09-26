package com.infoweaver.springtutorial.controller;

import com.infoweaver.springtutorial.util.SpringUtils;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ruobing Shang 2022-09-26 11:31
 */
public class CommonController {
    @GetMapping("/context")
    public String getContext() {
        return SpringUtils.getApplicationContext().getDisplayName();
    }

    @GetMapping("/redis-template")
    public String getBean() {
        return SpringUtils.getBean("redisTemplate").toString();
    }
}
