package com.infoweaver.springtutorial.rabbitmq;

import com.infoweaver.springtutorial.util.JacksonUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ruobing Shang 2022-10-04 14:41
 */
@Configuration
public class RabbitMqConfig {
    public static final String QUEUE_TOPIC = "rabbitmqDirectQueue";
    public static final String ROUTING_KEY = "rabbitmqDirectRoutingKey";
    public static final String EXCHANGE = "rabbitmqDirectExchange";

    /**
     * durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
     * exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     */
    @Bean
    public Queue rabbitmqDirectQueue() {
        return new Queue(QUEUE_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqDirectExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    /**
     * Bind the queue to the exchange and set the key for matching
     */
    @Bean
    public Binding bindDirect() {
        return BindingBuilder
                .bind(rabbitmqDirectQueue())
                .to(rabbitmqDirectExchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return JacksonUtils.getMessageConverter();
    }
}
