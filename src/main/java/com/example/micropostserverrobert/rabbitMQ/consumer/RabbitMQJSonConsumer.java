package com.example.micropostserverrobert.rabbitMQ.consumer;


import com.example.micropostserverrobert.entity.Message;
import com.example.micropostserverrobert.rabbitMQ.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJSonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(Message message){
        LOGGER.info(String.format("Received JSON message -> %s", message.toString()));
    }
}
