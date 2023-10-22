package com.example.micropostserverrobert.controller;

import com.example.micropostserverrobert.dto.MessageDto;
import com.example.micropostserverrobert.entity.Message;
import com.example.micropostserverrobert.rabbitMQ.dto.User;
import com.example.micropostserverrobert.rabbitMQ.publisher.RabbitMQJsonProducer;
import com.example.micropostserverrobert.rabbitMQ.publisher.RabbitMQProducer;
import com.example.micropostserverrobert.repository.MessageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    @Autowired
    private final MessageRepository repository;
    private RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(MessageRepository repository, RabbitMQJsonProducer jsonProducer) {
        this.repository = repository;
        this.jsonProducer = jsonProducer;
    }

//    @PostMapping("/publish")
//    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
//        jsonProducer.sendJsonMessage(user);
//        return ResponseEntity.ok("Json message sent to RabbitMQ ...");
//    }


    @PostMapping("/posts")
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) {
        message.setDateAndTime(java.time.LocalDateTime.now().toString());

        if (message.getDataAndTime() == null) {
            message.setDataAndTime(java.time.LocalDateTime.now().toString());
        }
        Message savedMessage = repository.save(message);

        jsonProducer.sendJsonMessage(savedMessage);
//        rabbitMQConsumer.publishMessage( message.getMessage());



        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(message));
    }

}
