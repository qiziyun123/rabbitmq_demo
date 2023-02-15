package com.qizy.springbootrabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {
    //监听队列
    @RabbitListener(queues = "test")
    public void listenMessage(Message message)
    {
        System.out.println("接收消息："+message);
        String s = new String(message.getBody());
        System.out.println("解析后的消息："+s);
    }
}
