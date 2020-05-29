package com.codecool.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private Status status;

    public Todo(String title, Status status) {
        this.status = status;
        this.title = title;
    }

    public Todo(String title) {
        this.status = Status.ACTIVE;
        this.title = title;
    }

    public boolean isCompleted() {
        return this.status == Status.COMPLETE;
    }

}
