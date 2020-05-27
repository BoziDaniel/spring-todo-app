package com.codecool.todoapp.controller;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private static final String SUCCESS = "{\"success\":true}";

    @Autowired
    private TodoDao todoDao;


    @PostMapping("/addTodo")
    public String addTodo(@RequestBody Todo todo) {
        todoDao.add(todo);
        return SUCCESS;
    }

    @PostMapping("/list")
    public List<Todo> allTodos(@RequestBody Todo todo) {
        return todoDao.all();
    }

    //remove all completed
    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        todoDao.removeCompleted();
        return SUCCESS;
    }

    //toggle all status
    @PutMapping("/todos/toggle_all")
    public String toggleAll(@RequestBody boolean complete) {
        todoDao.toggleAll(complete);
        return SUCCESS;
    }

    // Remove by id
    @DeleteMapping("/todos/{id}")
    public String removeById(@PathVariable Long id) {
        todoDao.remove(id);
        return SUCCESS;
    }

    // Update by id
    @PutMapping("/todos/{id}")
    public String updateById(@PathVariable Long id, @RequestBody String title) {
        todoDao.update(id, title);
        return SUCCESS;
    }

    // Find by id
    @GetMapping("/todos/{id}")
    public String findById(@PathVariable Long id) {
        return todoDao.find(id).getTitle();
    }

    // Toggle status by id
    @PutMapping("/todos/{id}/{toggle_status}")
    public String toggleStatusById(@PathVariable Long id, @PathVariable boolean status) {
        todoDao.toggleStatus(id, status);
        return SUCCESS;

    }
}
