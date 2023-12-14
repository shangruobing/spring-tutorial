package com.infoweaver.springtutorial.rabbitmq;

import com.infoweaver.springtutorial.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruobing Shang 2023-10-19 21:21
 */
@Slf4j
@Service
public class RabbitMqServiceImpl implements RabbitMqService {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public Map<String, Object> sendMessage(String content) {
        Map<String, Object> messageBody = new HashMap<>(8);
        messageBody.put("time", DateTimeUtils.getNowDateTime());
        messageBody.put("author", "Test User");
        messageBody.put("message", content);
        log.info("RabbitMQ Message: " + messageBody);
        rabbitTemplate.convertAndSend(RabbitMqConfiguration.EXCHANGE, RabbitMqConfiguration.ROUTING_KEY, messageBody);
        return messageBody;
    }
}
