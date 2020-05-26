package com.codecool.todoapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Todo {

    private String title;
    private String id;
    private Status status;
    private static int _idCounter = 0;

//    public boolean isCompleted() {
//        return this.status == Status.COMPLETE;
//    }

    public static Todo create(String title) {
        _idCounter++;
        return new Todo(title, String.valueOf(_idCounter), Status.ACTIVE);
    }

}
