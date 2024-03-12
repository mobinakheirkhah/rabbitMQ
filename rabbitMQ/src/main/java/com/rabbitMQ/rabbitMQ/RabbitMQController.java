package com.rabbitMQ.rabbitMQ;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/producer")
public class RabbitMQController {

    @Autowired
    private AmqpTemplate amqpTemplate;



    private static Logger logger = (LoggerFactory.getLogger(RabbitMQController.class));

    @PostMapping(value = "/direct" ,produces = "application/json")
    public String direct(@RequestBody RequestMessage requestMessage){

        amqpTemplate.convertAndSend(requestMessage.getExchange(),
                requestMessage.getRoutingKey(),requestMessage.getMessageData());
        logger.info("sending message to queue: " + requestMessage.toString() );

        return "Message is sent to rabbitMq";
    }

    @PostMapping(value = "/fanout" ,produces = "application/json")
    public String fanout(@RequestBody RequestMessage requestMessage){

        amqpTemplate.convertAndSend(requestMessage.getExchange(),
                "",requestMessage.getMessageData());
        logger.info("sending message to queue: " + requestMessage.toString() );

        return "Message is sent to rabbitMq";
    }

    @PostMapping(value = "/topic" ,produces = "application/json")
    public String topic(@RequestBody RequestMessage requestMessage){

        amqpTemplate.convertAndSend(requestMessage.getExchange(),
                requestMessage.getRoutingKey(),requestMessage.getMessageData());
        logger.info("sending message to queue: " + requestMessage.toString() );

        return "Message is sent to rabbitMq";
    }

    @PostMapping(value = "/header" ,produces = "application/json")
    public String header(@RequestBody RequestMessage requestMessage){

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("headerName", 1);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(requestMessage.getMessageData(), messageProperties);
        amqpTemplate.convertAndSend(requestMessage.getExchange(),
                " ",message);
        logger.info("sending message to queue: " + requestMessage.toString() );

        return "Message is sent to rabbitMq";
    }


}
