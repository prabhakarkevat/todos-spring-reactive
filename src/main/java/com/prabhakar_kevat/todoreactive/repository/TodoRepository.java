package com.prabhakar_kevat.todoreactive.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.prabhakar_kevat.todoreactive.entity.TodoEntity;

import reactor.core.publisher.Flux;

public interface TodoRepository extends ReactiveCrudRepository<TodoEntity, Long> {
    Flux<TodoEntity> findByCompleted(Boolean completed);
}
