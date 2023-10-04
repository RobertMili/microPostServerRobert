package com.example.micropostserverrobert.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@Entity
public class Message {

    public Message() {}


    public Message(Long id, String fromUser, String toUserName, String message, String dataAndTime) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUserName = toUserName;
        this.message = message;
        this.dataAndTime = dataAndTime;
    }

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String fromUser;
    private String toUserName;
    private String message;
    private String dataAndTime;


    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", message='" + message + '\'' +
                ", dataAndTime='" + dataAndTime + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
