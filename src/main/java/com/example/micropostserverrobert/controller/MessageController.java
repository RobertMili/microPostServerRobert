package com.example.micropostserverrobert.controller;


import com.example.micropostserverrobert.entity.Message;
import com.example.micropostserverrobert.rabbitMQ.publisher.RabbitMQJsonProducer;
import com.example.micropostserverrobert.repository.MessageRepository;
import com.example.micropostserverrobert.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;


@RestController
@RequestMapping("/")
@CrossOrigin
public class MessageController {

    @Autowired
    private final MessageRepository repository;
    private final MessageService messageService;

    private final RabbitMQJsonProducer jsonProducer;

    public MessageController(MessageRepository repository, MessageService messageService, RabbitMQJsonProducer jsonProducer) {
        this.repository = repository;
        this.messageService = messageService;
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/posts")
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) {
        message.setDateAndTime(java.time.LocalDateTime.now().toString());

        if (message.getDataAndTime() == null) {
            message.setDataAndTime(java.time.LocalDateTime.now().toString());
        }
        Message savedMessage = repository.save(message);
        jsonProducer.sendJsonMessage(savedMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(message));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Not valid due to validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/posts/{id}")
    public ResponseEntity<Optional<Message>> getMessage( @PathVariable(value = "id") Long id) {
        Optional<Message> fromTo = repository.findById(id);
        if (fromTo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(fromTo);
    }





}

