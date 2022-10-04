package com.infoweaver.springtutorial.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruobing Shang 2022-10-04 14:17
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    private final RabbitMqService rabbitMqService;

    @Autowired
    public RabbitController(RabbitMqService rabbitMqService) {
        this.rabbitMqService = rabbitMqService;
    }

    @PostMapping("/")
    public void send(@RequestParam(
            value = "message", defaultValue = "This is a message.", required = false) String message) {
        rabbitMqService.sendMessage(message);
    }
}
