package com.rabbitMQ.rabbitMQ;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Getter
@Setter
public class RequestMessage implements Serializable {

    private String exchange ;
    private String routingKey;
    private String messageData;

    @Override
    public String toString() {
        return "RequestMessage{" +
                "exchange='" + exchange + '\'' +
                ", routingKey='" + routingKey + '\'' +
                ", messageData='" + messageData + '\'' +
                '}';
    }
}
