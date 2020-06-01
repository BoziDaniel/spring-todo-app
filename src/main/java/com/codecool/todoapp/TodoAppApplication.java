package com.codecool.todoapp;

import com.codecool.todoapp.model.*;
import com.codecool.todoapp.repository.TodoRepository;
import com.codecool.todoapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class TodoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TodoAppApplication.class);
    private final PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;
    @Bean
    @Profile("dev")
    public CommandLineRunner init() {
        return args -> {
            todoRepository.save(new Todo("first item", Status.ACTIVE));
            todoRepository.save(new Todo("2 item", Status.COMPLETE));
            todoRepository.save(new Todo("3 item", Status.ACTIVE));
            userRepository.save(new User("admin", passwordEncoder.encode("admin"), Arrays.asList("Admin", "User")));
            userRepository.save(new User("user", passwordEncoder.encode("user"), Arrays.asList("User")));
        };

    }
}
