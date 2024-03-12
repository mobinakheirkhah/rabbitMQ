package com.rabbitMQ.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
@Profile("header")

public class RabbitMQHeaderConfig {


    @Bean
    Queue queueA() {
        return new Queue("queue-A",true);
    }

    @Bean
    Queue queueB() {
        return new Queue("queue-B",true);
    }

    @Bean
    Queue queueC() {
        return new Queue("queue-C",true);
    }

    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange("header-exchange");
    }


    @Bean
    Binding queueABinding(Queue queueA , HeadersExchange headersExchange){

        return new Binding("queue-A", Binding.DestinationType.QUEUE,
                "header-exchange", null, Map.of("headerName", 1));
    }

    @Bean
    Binding queueBBinding(Queue queueB ,HeadersExchange headersExchange){
//        return BindingBuilder.bind(queueB).to(headersExchange).where("headerName").matches("headerOfQueueB");
        return new Binding(queueB.getName(), Binding.DestinationType.QUEUE,
                headersExchange.getName(), null, Map.of("headerName", "headerOfQueueB"));
    }

    @Bean
    Binding queueCBinding(Queue queueC ,HeadersExchange headersExchange){
//        return BindingBuilder.bind(queueC).to(headersExchange).where("headerName").matches("headerOfQueueC");
        return new Binding(queueC.getName(), Binding.DestinationType.QUEUE,
                headersExchange.getName(), null, Map.of("headerName", "headerOfQueueC"));
    }

}
