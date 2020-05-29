package com.codecool.todoapp.repository;

import com.codecool.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findTodoById(Long id);

}
