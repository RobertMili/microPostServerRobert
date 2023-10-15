package com.example.micropostserverrobert.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private Long id;

    public MessageDto() {

    }

    public MessageDto(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id=" + id +
                '}';
    }
}
