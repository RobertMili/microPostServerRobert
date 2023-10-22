package com.example.micropostserverrobert;


import com.example.micropostserverrobert.entity.Message;
import com.example.micropostserverrobert.repository.MessageRepository;
import io.restassured.RestAssured;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.text.IsEmptyString.emptyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.restassured.response.Response;

@SpringBootTest
class MicroPostServerRobertApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @MockBean
    private MessageRepository messageRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void testCreatMessage() throws Exception {

        Message message1 = new Message();
        message1.setId(1L);
        message1.setFromUser("fromUser");
        message1.setToUserName("toUserName");
        message1.setMessage("message");
        message1.setDataAndTime("dataAndTime");


       when(messageRepository.findById(1L)).thenReturn(Optional.of(message1));

        mockMvc.perform(get("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void getMessageByIdNotExistsReturn404() throws Exception {

        mockMvc.perform(get("/posts/2"))
                .andExpect(status().isNotFound());
    }

}


