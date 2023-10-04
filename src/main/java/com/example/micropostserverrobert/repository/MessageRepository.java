package com.example.micropostserverrobert.repository;

import com.example.micropostserverrobert.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MessageRepository extends ListCrudRepository<Message,Long> {

    @EntityGraph(value = "Organization.persons")
    List<Message> findAllBy();

}

