package com.qizy.springbootrabbitmq.config;

public class MqConstant {

    /**
     * Direct模式 测试队列名称
     */
    public static final String TEST_DIRECT_QUEUE = "test_direct_queue";
    /**
     * Direct模式 测试交换器名称
     */
    public static final String TEST_DIRECT_EXCHANGE = "test_direct_exchange";
    /**
     * Direct模式 测试路由键
     */
    public static final String TEST_DIRECT_ROUTING_KEY = "test_direct_routing_key";

    /**
     * fanout模式 测试队列名称
     */
    public static final String TEST_FANOUT_QUEUE = "test_fanout_queue";
    /**
     * fanout模式 测试交换器名称
     */
    public static final String TEST_FANOUT_EXCHANGE = "test_fanout_exchange";


    public static final String DELAY_QUEUE = "test_delay_queue"; //延时队列名称
    public static final String DELAY_EXCHANGE = "test_delay_exchange"; //交换器名称
    public static final String DELAY_ROUTING_KEY = "test_delay_routing_key"; //路由键
}
