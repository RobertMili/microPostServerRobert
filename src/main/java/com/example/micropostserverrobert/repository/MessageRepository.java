package com.example.micropostserverrobert.repository;

import com.example.micropostserverrobert.entity.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MessageRepository extends ListCrudRepository<Message,Long> {

    @EntityGraph(value = "Organization.persons")
    List<Message> findAllBy();



}


