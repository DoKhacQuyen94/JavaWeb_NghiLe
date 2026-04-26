package org.example.todoapp.service;

import org.example.todoapp.dto.TodoDTO;
import org.example.todoapp.model.Todo;

import java.util.List;

public interface ITodoService {
    List<Todo> getAllTodo();

    void addTodo(TodoDTO newTodo);

    void updateTodo(TodoDTO newTodo);
    void deleteTodo(Long id);
}
