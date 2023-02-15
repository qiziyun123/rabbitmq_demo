package com.qizy.springbootrabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSimpleQueueProducer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 简单直接发送给队列
     */
    @RequestMapping("testSimpleQueueProducer")
    public void testSimpleQueueProducer(){
        // 直接发送给队列
        rabbitTemplate.convertAndSend("test","zxcvb");
    }
}
