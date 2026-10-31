package com.prabhakar_kevat.todoreactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prabhakar_kevat.todoreactive.Constants;
import com.prabhakar_kevat.todoreactive.model.TodoDTO;
import com.prabhakar_kevat.todoreactive.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

        private final TodoService todoService;

        @GetMapping
        public Flux<TodoDTO> getTodos() {
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

        @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public Flux<TodoDTO> streamTodos() {
                log.info(Constants.STARTED + Constants.SPACE_DELIMITER + Constants.STREAMING + Constants.SPACE_DELIMITER
                                + Constants.TODOS);
                return todoService.getTodos().delayElements(java.time.Duration.ofMillis(500))
                                .doFinally(c -> log.info(Constants.COMPLETED + Constants.SPACE_DELIMITER + "STREAMING"
                                                + Constants.SPACE_DELIMITER + Constants.TODOS));
        }

        @GetMapping("/{id}")
        public Mono<TodoDTO> getTodo(@PathVariable Long id) {
                return todoService.getTodo(id);
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public Mono<TodoDTO> saveTodos(@RequestBody TodoDTO todo) {
                log.info(Constants.STARTED
                                + Constants.SPACE_DELIMITER
                                + Constants.CREATING
                                + Constants.SPACE_DELIMITER
                                + Constants.TODOS);

                return todoService.saveTodo(todo).doFinally(c -> log.info(Constants.COMPLETED
                                + Constants.SPACE_DELIMITER
                                + Constants.CREATING
                                + Constants.SPACE_DELIMITER
                                + Constants.TODOS));
        }

        @PutMapping("/{id}")
        public Mono<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todo) {
                return todoService.updateTodo(id, todo);
        }

        @DeleteMapping("/{id}")
        public Mono<Void> deleteTodo(@PathVariable Long id) {
                return todoService.deleteTodo(id);
        }
}
