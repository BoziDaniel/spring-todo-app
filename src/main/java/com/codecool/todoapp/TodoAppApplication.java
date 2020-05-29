package com.codecool.todoapp;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
import com.codecool.todoapp.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class TodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TodoAppApplication.class);

    @Autowired
    private TodoRepository todoRepository;
    @Bean
    @Profile("dev")
    public CommandLineRunner init() {
        return args -> {
            todoRepository.save(new Todo("first item", Status.ACTIVE));
            todoRepository.save(new Todo("2 item", Status.COMPLETE));
            todoRepository.save(new Todo("3 item", Status.ACTIVE));
        };

    }
}
