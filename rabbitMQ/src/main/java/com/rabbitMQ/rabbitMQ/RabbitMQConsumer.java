package com.rabbitMQ.rabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component

public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

////for direct test
    @RabbitListener(queues = "queue-A")
    public void consume1(String requestMessage){
        LOGGER.info("Received message "  + requestMessage);
    }
///for fanout test
    @RabbitListener(queues = { "queue-A","queue-B", "queue-C" })
    public void consume2(String requestMessage){
        LOGGER.info("Received message "  + requestMessage);
    }
///for topic test
    @RabbitListener(queues = { "queue-A","queue-B", "queue-C" })
    public void consume3(String requestMessage){
        LOGGER.info("Received message from queue "  + requestMessage);
    }

//    @RabbitListener(queues = "queue-B")
//    public void consume4(String requestMessage){
//        LOGGER.info("Received message from queueB "  + requestMessage);
//    }
//
//    @RabbitListener(queues = "queue-C")
//    public void consume5(String requestMessage){
//        LOGGER.info("Received message from queueC "  + requestMessage);
//    }

    ///for header test
    @RabbitListener(queues = "queue-A")
    public void consume6(Message requestMessage){
        LOGGER.info("Received message "  + requestMessage);
    }

}
