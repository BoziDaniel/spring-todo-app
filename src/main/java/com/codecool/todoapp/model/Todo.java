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

    private String title;
    @Id
    @GeneratedValue
    private String id;
    private Status status;

    public boolean isCompleted() {
        return this.status == Status.COMPLETE;
    }



}
