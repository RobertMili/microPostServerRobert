package com.example.micropostserverrobert;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MicroPostServerRobertApplication implements CommandLineRunner {

//
//    @Autowired
//    private RabbitConnection rabbitMQConsumer;


    public static void main(String[] args) {
        SpringApplication.run(MicroPostServerRobertApplication.class, args);



    }

    @Override
    public void run(String... args) throws Exception {

    }
}
