package com.example.todoreactive.service;

import com.example.todoreactive.entity.TodoEntity;
import com.example.todoreactive.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Flux<TodoEntity> getTodos() {
        return todoRepository.findAll();
    }

    public Mono<TodoEntity> saveTodo(TodoEntity todoEntity) {
        return todoRepository.save(todoEntity);
    }
}
