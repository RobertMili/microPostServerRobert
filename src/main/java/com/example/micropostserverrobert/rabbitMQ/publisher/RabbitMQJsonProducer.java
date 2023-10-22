package com.example.micropostserverrobert.rabbitMQ.publisher;

import com.example.micropostserverrobert.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Message message){
        LOGGER.info(String.format("Json message sent -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, message);
    }
    public void getMessage(Message message){
        LOGGER.info(String.format("Json message sent -> %s", message.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, message);
    }
}
