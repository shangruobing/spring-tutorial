package com.infoweaver.springtutorial.rabbitmq;

/**
 * @author Ruobing Shang 2022-10-04 20:44
 */
public interface RabbitMqService {
    /**
     * Send a message to RabbitMQ.
     * @param message a message
     * @return status
     */
    boolean sendMessage(String message);
}
