package com.example.micropostserverrobert.controller;


import com.example.micropostserverrobert.model.Message;
import com.example.micropostserverrobert.repository.MessageRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/")
@CrossOrigin

public class MessageController {

   @Autowired
   private final MessageRepository repository;


    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/posts")
    public ResponseEntity<Message> createMessage(@Valid @RequestBody Message message) {
        Message savedMessage = repository.save(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(message));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(
            org.springframework.web.bind.MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("Not valid due to validation error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/posts")
    public Message getMessage(  ) {

        Message message = new Message();
        message.setDataAndTime(LocalDateTime.now().toString());
        return message;
    }

}
