package com.example.todoreactive.controller;

import com.example.todoreactive.Constants;
import com.example.todoreactive.entity.TodoEntity;
import com.example.todoreactive.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public Flux<TodoEntity> getTodos() {
        log.info(Constants.STARTED
                + Constants.SPACE_DELIMITER
                + Constants.RETRIEVING
                + Constants.SPACE_DELIMITER
                + Constants.TODOS);

        return todoService.getTodos().doFinally(c -> log.info(Constants.COMPLETED
                + Constants.SPACE_DELIMITER
                + Constants.RETRIEVING
                + Constants.SPACE_DELIMITER
                + Constants.TODOS));
    }
}
