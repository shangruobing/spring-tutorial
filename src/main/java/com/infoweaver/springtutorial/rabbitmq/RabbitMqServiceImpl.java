package com.infoweaver.springtutorial.rabbitmq;

import com.infoweaver.springtutorial.util.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-10-04 20:41
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
    public boolean sendMessage(String content) {
        try {
            Map<String, Object> messageBody = new HashMap<>(8);
            messageBody.put("time", DateTimeUtils.getNowDateTime());
            messageBody.put("author", "Default User");
            messageBody.put("message", content);
            log.info("RabbitMQ Message: " + messageBody);
            rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTING_KEY, messageBody);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }
}
