package com.codecool.todoapp.service;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public void removeCompleted() {
        todoRepository.findAll()
                .forEach(todo -> {
                    if (todo.isCompleted()) {
                        todoRepository.delete(todo);
                    }
                });
    }

    public void toggleAll(boolean isComplete) {
        todoRepository.findAll().forEach(todo -> {
            todo.setStatus(isComplete ? Status.COMPLETE : Status.ACTIVE);
            todoRepository.save(todo);
        });
    }

    public void removeById(Long id) {
        todoRepository.findAll().forEach(todo -> {
            if (todo.getId().equals(id)) {
                todoRepository.delete(todo);
            }
        });
    }

    public void update(Long id, String title) {
        System.out.println(title + " " + title);
        todoRepository.findAll().forEach(todo -> {
            if (todo.getId().equals(id)) {
                todo.setTitle(title);
                todoRepository.save(todo);
            }
        });
    }

    public String findById(Long id) {
        Todo todo = todoRepository.findTodoById(id);
        return todo.getTitle();
    }

    public List<Todo> ofStatus(String statusString) {
        return (statusString == null || statusString.isEmpty()) ? todoRepository.findAll() : ofStatus(Status.valueOf(statusString.toUpperCase()));
    }

    public List<Todo> ofStatus(Status status) {
        return todoRepository.findAll().stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }

    public void toggleStatusById(Long id, boolean isComplete) {
        Todo todo = todoRepository.findTodoById(id);
        if (isComplete) {
            todo.setStatus(Status.COMPLETE);
        } else {
            todo.setStatus(Status.ACTIVE);
        }
        todoRepository.save(todo);
    }

    public void addTodo(String title) {
        Todo todo = Todo.builder()
                .status(Status.ACTIVE)
                .title(title)
                .build();
        todoRepository.save(todo);
    }
}
