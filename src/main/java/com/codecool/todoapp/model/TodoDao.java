package com.codecool.todoapp.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Component
public class TodoDao {

    private final List<Todo> DATA = new ArrayList<>();

    public void add(Todo todo) {
        DATA.add(todo);
    }

    public Todo find(Long id) {
        return DATA.stream().filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void update(Long id, String title) {
        find(id).setTitle(title);
    }

    public List<Todo> ofStatus( String statusString) {
        System.out.println(statusString);
        return (statusString == null || statusString.isEmpty()) ? DATA : ofStatus(Status.valueOf(statusString.toUpperCase()));
    }

    public List<Todo> ofStatus(Status status) {
        return DATA.stream().filter(t -> t.getStatus().equals(status)).collect(Collectors.toList());
    }

    public void remove(Long id) {
        DATA.remove(find(id));
    }

    public void removeCompleted() {
        ofStatus(Status.COMPLETE).forEach(t -> this.remove(t.getId()));
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Todo todo = find(id);
        if (isComplete) {
            todo.setStatus(Status.COMPLETE);
        } else {
            todo.setStatus(Status.ACTIVE);
        }
    }

    public void toggleAll(boolean complete) {
        this.all().forEach(t -> t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE));
    }

    public List<Todo> all() {
        return DATA;
    }
}
