package com.codecool.todoapp.controller;

import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
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
    public String allTodos(@RequestBody String status) throws JSONException {
//        return todoDao.all();
        System.out.println(status);
        List<Todo> todos = todoDao.ofStatus(status);
        JSONArray arr = new JSONArray();
        for (Todo todo : todos) {
            JSONObject jo = new JSONObject();
            jo.put("id", todo.getId());
            jo.put("title", todo.getTitle());
            jo.put("completed", todo.isCompleted());
            arr.put(jo);
        }
        System.out.println( arr.toString(2));
        return arr.toString(2);
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
