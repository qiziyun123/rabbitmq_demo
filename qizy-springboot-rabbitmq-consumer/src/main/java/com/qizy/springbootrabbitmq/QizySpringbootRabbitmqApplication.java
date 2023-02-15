package com.qizy.springbootrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * pom引入了amqp
 * @RabbitAutoConfiguration
 */
@SpringBootApplication
public class QizySpringbootRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(QizySpringbootRabbitmqApplication.class, args);
    }

}
