package net.jazbelt.springboot3security.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

record Todo (String username, String description) {}

@RestController
public class TodoController {

    private Logger logger = LoggerFactory.getLogger(TodoController.class);

    private static final List<Todo> TODOS_LIST = List.of(
            new Todo("jdoe", "Learn AWS"),
            new Todo("jdoe", "Learn Springboot")
        );

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return TODOS_LIST;
    }

    @GetMapping("/users/{username}/todos")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    public Todo getTodosByUser(@PathVariable String username) {
        return TODOS_LIST.get(0);
    }

    @PostMapping("/users/{username}/todos")
    public void psotTodoByUser(@PathVariable String username,
                               @RequestBody Todo todo) {
        logger.info("Creating {} for {}", todo, username);
        //TODOS_LIST.add(todo);
    }
}
