package com.infoweaver.springtutorial.rabbitmq;

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

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Ruobing Shang 2022-10-04 14:19
 */
@Slf4j
@Component
public class Consumer {
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue(RabbitMqConfig.QUEUE_TOPIC))
    public void receive(Message message) throws MessageConversionException {
        try {
            log.info("=== Consumers are working ===");
            log.info(StringUtils.bytesToString(message.getBody()));
            HashMap<?, ?> map = JacksonUtils.jsonToHashMap(StringUtils.bytesToString(message.getBody()));
            log.info(String.valueOf(map));
            log.info("message:" + map.get("message").toString());

        } catch (AmqpException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}