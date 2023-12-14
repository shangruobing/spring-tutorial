package com.infoweaver.springtutorial.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Ruobing Shang 2023-10-19 21:21
 */
@RestController
public class RabbitMqController {
    private final RabbitMqServiceImpl rabbitMqService;

    @Autowired
    public RabbitMqController(RabbitMqServiceImpl rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PostMapping("/rabbit")
    public Map<String, Object> send(@RequestParam(value = "message", defaultValue = "This is test message.", required = false) String message) {
        return rabbitMqService.sendMessage(message);
    }
}
