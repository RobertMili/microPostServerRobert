package com.example.micropostserverrobert.controller;


import com.example.micropostserverrobert.model.Message;
import com.example.micropostserverrobert.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/")
@CrossOrigin

public class MessageController {

    private final com.example.micropostserverrobert.repository.MessageRepository repository;


    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/posts")
    public Message createMessage(@RequestBody Message message) {
        message.setDataAndTime(LocalDateTime.now().toString());
        return repository.save(message);
    }
    @GetMapping("/posts")
    public Message getMessage(  ) {

        Message message = new Message();
        message.setDataAndTime(LocalDateTime.now().toString());
        return message;
    }

}
