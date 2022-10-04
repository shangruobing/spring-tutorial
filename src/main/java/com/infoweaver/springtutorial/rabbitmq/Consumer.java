//package com.infoweaver.springtutorial.rabbitmq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @author Ruobing Shang 2022-10-04 14:19
// */
//@Slf4j
//@Component
//@RabbitListener(queuesToDeclare = @Queue("hello"))
//public class Consumer {
//
//    @RabbitHandler
//    public void receive(String message) {
//        log.info("我的消息队列");
//        System.out.println(message);
//        log.info(message);
//    }
//}
