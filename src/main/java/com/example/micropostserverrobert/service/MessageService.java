package com.example.micropostserverrobert.service;


import com.example.micropostserverrobert.dto.MessageDto;
import com.example.micropostserverrobert.entity.Message;
import com.example.micropostserverrobert.repository.MessageRepository;
import org.springframework.stereotype.Service;



@Service
public class MessageService {

    public MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String getMessage(Long messageId) {
        return messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found")).getMessage();
    }


}

