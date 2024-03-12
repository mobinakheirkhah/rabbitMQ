package com.rabbitMQ.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
@Profile("topic")

public class RabbitMQTopicConfig {


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
    TopicExchange topicExchange(){
        return new TopicExchange("topic-exchange");
    }


    @Bean
    Binding queueABinding(Queue queueA , TopicExchange topicExchange){
        return BindingBuilder.bind(queueA).to(topicExchange).with("quick.*.*");
    }

    @Bean
    Binding queueBBinding(Queue queueB , TopicExchange topicExchange){
        return BindingBuilder.bind(queueB).to(topicExchange).with("*.orange.*");
    }

    @Bean
    Binding queueCBinding(Queue queueC , TopicExchange topicExchange){
        return BindingBuilder.bind(queueC).to(topicExchange).with("*.*.rabbit");
    }

}
