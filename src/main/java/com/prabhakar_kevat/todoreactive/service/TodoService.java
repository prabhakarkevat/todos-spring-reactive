package com.prabhakar_kevat.todoreactive.service;

import org.springframework.stereotype.Service;

import com.prabhakar_kevat.todoreactive.entity.TodoEntity;
import com.prabhakar_kevat.todoreactive.mapper.TodoMapper;
import com.prabhakar_kevat.todoreactive.model.TodoDTO;
import com.prabhakar_kevat.todoreactive.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper mapper;

    public Flux<TodoDTO> getTodos() {
        return todoRepository.findAll().map(mapper::entityToDto);
    }

    public Mono<TodoDTO> saveTodo(TodoDTO todo) {
        TodoEntity todoEntity = mapper.dtoToEntity(todo);
        return todoRepository.save(todoEntity).map(mapper::entityToDto);
    }
}
