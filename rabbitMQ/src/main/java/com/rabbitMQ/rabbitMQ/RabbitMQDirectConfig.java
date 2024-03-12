package com.rabbitMQ.rabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableRabbit
@Profile("direct")

public class RabbitMQDirectConfig {

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
    DirectExchange directExchange(){
        return new DirectExchange("direct-exchange");
    }




    @Bean
    Binding queueABinding(Queue queueA , DirectExchange directExchange){
        return BindingBuilder.bind(queueA).to(directExchange).with("routing-a");
    }

    @Bean
    Binding queueBBinding(Queue queueB , DirectExchange directExchange){
        return BindingBuilder.bind(queueB).to(directExchange).with("routing-b");
    }

    @Bean
    Binding queueCBinding(Queue queueC , DirectExchange directExchange){
        return BindingBuilder.bind(queueC).to(directExchange).with("routing-c");
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;

    }
    @Bean
    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMaxConcurrentConsumers(5);
        return factory;
    }

}
