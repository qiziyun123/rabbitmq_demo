package com.qizy.springbootrabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitmqConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 消费确认模式需要
     * @return
     */
    @Bean
    public RabbitTemplate initRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

        //设置Json转换器
        rabbitTemplate.setMessageConverter(messageConverter());
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        //确认消息送到交换机(Exchange)回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // 通常不需要开启，一般都是用回调机制，当然也可以开启，数据库记录一张mq消息表，专门记录错误信息等
//            System.out.println(" 确认消息送到交换机(Exchange)结果：");
//
//            System.out.println("相关数据：" + correlationData);
//            System.out.println("是否成功：" + ack);
//            System.out.println("错误原因：" + cause);
        });

        //确认消息送到队列(Queue)回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback()
        {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage)
            {
                // 通常不需要开启，一般都是用回调机制，当然也可以开启，数据库记录一张mq消息表，专门记录错误信息等
//                System.out.println(" 确认消息送到队列(Queue)结果：");
//                System.out.println("发生消息：" + returnedMessage.getMessage());
//                System.out.println("回应码：" + returnedMessage.getReplyCode());
//                System.out.println("回应信息：" + returnedMessage.getReplyText());
//                System.out.println("交换机：" + returnedMessage.getExchange());
//                System.out.println("路由键：" + returnedMessage.getRoutingKey());
            }
        });
        return rabbitTemplate;
    }



    /**
     * Direct交换器
     * 创建交换器，参数说明：
     * String name：交换器名称
     * boolean durable：设置是否持久化，默认是 false。durable 设置为 true 表示持久化，反之是非持久化。
     * 持久化可以将交换器存盘，在服务器重启的时候不会丢失相关信息。
     * boolean autoDelete：设置是否自动删除，为 true 则设置队列为自动删除
     */
    @Bean
    public DirectExchange testDirectExchange()
    {
       return new DirectExchange(MqConstant.TEST_DIRECT_EXCHANGE, true, false);
    }

    /**
     * Direct队列
     * 创建队列，参数说明：
     * String name：队列名称。
     * boolean durable：设置是否持久化，默认是 false。durable 设置为 true 表示持久化，反之是非持久化。
     * 持久化的队列会存盘，在服务器重启的时候不会丢失相关信息。
     * boolean exclusive：设置是否排他，默认也是 false。为 true 则设置队列为排他。
     * boolean autoDelete：设置是否自动删除，为 true 则设置队列为自动删除，
     * 当没有生产者或者消费者使用此队列，该队列会自动删除。
     * Map<String, Object> arguments：设置队列的其他一些参数。
     */
    @Bean
    public Queue testDirectQueue()
    {
        return new Queue(MqConstant.TEST_DIRECT_QUEUE, true, false, false, null);
    }


    /**
     * fanout交换机
     * @return
     */
    @Bean
    public FanoutExchange testFanoutExchange()
    {
        return new FanoutExchange(MqConstant.TEST_FANOUT_EXCHANGE, true, false);
    }

    /**
     * fanout队列
     * 创建队列，参数说明：
     * String name：队列名称。
     * boolean durable：设置是否持久化，默认是 false。durable 设置为 true 表示持久化，反之是非持久化。
     * 持久化的队列会存盘，在服务器重启的时候不会丢失相关信息。
     * boolean exclusive：设置是否排他，默认也是 false。为 true 则设置队列为排他。
     * boolean autoDelete：设置是否自动删除，为 true 则设置队列为自动删除，
     * 当没有生产者或者消费者使用此队列，该队列会自动删除。
     * Map<String, Object> arguments：设置队列的其他一些参数。
     */
    @Bean
    public Queue testFanoutQueue()
    {
        return new Queue(MqConstant.TEST_FANOUT_QUEUE, true, false, false, null);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding testFanoutBinding(FanoutExchange testFanoutExchange, Queue testFanoutQueue)
    {
        //将队列和交换机绑定, 并设置用于匹配键
        return BindingBuilder.bind(testFanoutQueue).to(testFanoutExchange);
    }

}
