package com.infoweaver.springtutorial.rabbitmq;

import java.util.Map;

/**
 * @author Ruobing Shang 2023-10-19 21:23
 */
public interface RabbitMqService {
    /**
     * 发送消息测试连通性
     * @param message 消息
     * @return 是否发送成功
     */
    Map<String, Object> sendMessage(String message);
}
