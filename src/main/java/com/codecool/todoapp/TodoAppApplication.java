package com.codecool.todoapp;

import com.codecool.todoapp.model.Status;
import com.codecool.todoapp.model.Todo;
import com.codecool.todoapp.model.TodoDao;
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
    private TodoDao todoDao;

    @Bean
    @Profile("dev")
    public CommandLineRunner init() {
        return args -> {
            todoDao.add(new Todo("first item", Status.ACTIVE));
            todoDao.add(new Todo("2 item", Status.ACTIVE));
            todoDao.add(new Todo("3 item", Status.ACTIVE));
            System.out.println(todoDao.toString());
        };

    }
}
