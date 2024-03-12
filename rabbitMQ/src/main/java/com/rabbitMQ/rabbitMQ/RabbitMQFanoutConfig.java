package com.rabbitMQ.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
@Profile("fanout")
public class RabbitMQFanoutConfig {


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
    Exchange fanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }


    @Bean
    Binding queueABinding(Queue queueA ,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    Binding queueBBinding(Queue queueB , FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }

    @Bean
    Binding queueCBinding(Queue queueC ,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueC).to(fanoutExchange);
    }

}
