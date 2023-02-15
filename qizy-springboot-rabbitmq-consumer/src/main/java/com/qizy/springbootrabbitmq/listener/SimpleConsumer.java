package com.qizy.springbootrabbitmq.listener;

import com.qizy.springbootrabbitmq.config.MqConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;

import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SimpleConsumer {
    /**
     * 手动ack
     *
     * @param message
     * @param deliveryTag
     * @param channel
     * @throws IOException
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = MqConstant.TEST_FANOUT_QUEUE, durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = MqConstant.TEST_FANOUT_EXCHANGE, type = ExchangeTypes.FANOUT)), ackMode = "MANUAL")
    public void consumerDoAck(String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel){
        try {
            // @Exchange(value = MqConstant.TEST_FANOUT_EXCHANGE, type = ExchangeTypes.FANOUT), key = "r") key是指的routing key
            System.out.println("接收到的MQ消息：" + message);
//            // 故意制造错误，为了走unack
//            System.out.println("处理业务" + 1 / 0);
            //手动Ack
            /**
             * 手动Ack参数说明
             * basicAck(long deliveryTag, boolean multiple)
             * deliveryTag：批量处理的标号，举例：这个队列现在有5条消息要消费，那么这批数据会标号从1-5递增，5的时候就会手动Ack  multiple：是否批量处理
             *
             */
//            System.out.println("deliveryTag：" + deliveryTag);
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            e.printStackTrace();
            /**
             * basicNack(long deliveryTag, boolean multiple, boolean requeue)
             * requeue：是否送回队列
             */
            try {
                // // 第三个参数true，表示这个消息会重新进入队列
                channel.basicNack(deliveryTag, false, true);
                // 可以考虑记录错误日志
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
