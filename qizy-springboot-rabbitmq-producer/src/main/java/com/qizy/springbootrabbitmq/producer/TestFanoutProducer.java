package com.qizy.springbootrabbitmq.producer;

import com.qizy.springbootrabbitmq.config.MqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestFanoutProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * fanout模式
     */
    @RequestMapping("testFanoutProducer")
    public void testFanoutProducer(String msg){
        // 注意参数顺讯convertAndSend(exchangeName, routeKey, body)
        rabbitTemplate.convertAndSend(MqConstant.TEST_FANOUT_EXCHANGE,null,msg);

    }
}
