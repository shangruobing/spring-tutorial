package com.infoweaver.springtutorial.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.infoweaver.springtutorial.util.JacksonUtils;
import com.infoweaver.springtutorial.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Ruobing Shang 2023-10-19 21:24
 */
@Slf4j
@Component
public class RabbitMqConsumer {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfiguration.QUEUE_TOPIC))
    public void receive(Message message) throws MessageConversionException {
        try {
            log.info("=== RabbitMQ Consumer begin work ===");
            log.info(StringUtils.bytesToString(message.getBody()));
            HashMap<?, ?> map = JacksonUtils.jsonToHashMap(StringUtils.bytesToString(message.getBody()));
            log.info(String.valueOf(map));
            log.info("message:" + map.get("message").toString());
            log.info("=== RabbitMQ Consumer finish work ===");
            log.info("=== RabbitMQ Consumer begin sleep ===");
            /**
             * 处理完一次，休眠30s，模拟提现动作
             */
            Thread.sleep(30 * 1000);
            log.info("=== RabbitMQ Consumer end sleep ===");
        } catch (AmqpException | JsonProcessingException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}

