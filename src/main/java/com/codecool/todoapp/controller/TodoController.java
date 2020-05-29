package com.codecool.todoapp.controller;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.service.TodoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    private static final String SUCCESS = "{\"success\":true}";


    @Autowired
    private TodoService todoService;

    //add new
    @PostMapping(value = "/addTodo")
    public String addTodo(@RequestParam("todo-title") String title) {
        todoService.addTodo(title);
        return SUCCESS;
    }

    //list all with chosen status
    @PostMapping("/list")
    public List<Todo> allTodos(@RequestParam("status") String status) throws JSONException {
        return todoService.ofStatus(status);
    }

    //remove all completed
    @DeleteMapping("/todos/completed")
    public String deleteCompleted() {
        todoService.removeCompleted();
        return SUCCESS;
    }

    //toggle all status
    @PutMapping(value = "/todos/toggle_all")
    public String toggleAll(@RequestParam("toggle-all") boolean isComplete) {
        todoService.toggleAll(isComplete);
        return SUCCESS;
    }

    // Remove by id
    @DeleteMapping("/todos/{id}")
    public String removeById(@PathVariable Long id) {
        todoService.removeById(id);
        return SUCCESS;
    }

    // Update by id
    @PutMapping("/todos/{id}")
    public String updateById(@PathVariable Long id, @RequestParam("todo-title") String title) {
        todoService.update(id, title);
        return SUCCESS;
    }

    // Find by id
    @GetMapping("/todos/{id}")
    public String findById(@PathVariable Long id) {
        return todoService.findById(id);
    }

    // Toggle status by id
    @PutMapping("/todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable Long id, @RequestParam("status") boolean isComplete) {
        todoService.toggleStatusById(id, isComplete);
        return SUCCESS;

    }
}
