package com.example.todoreactive.repository;

import com.example.todoreactive.entity.TodoEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends ReactiveMongoRepository<TodoEntity, Long> { }
