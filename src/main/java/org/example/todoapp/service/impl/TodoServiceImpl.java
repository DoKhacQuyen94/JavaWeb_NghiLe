package org.example.todoapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.todoapp.dto.TodoDTO;
import org.example.todoapp.model.Todo;
import org.example.todoapp.repository.ITodoRepository;
import org.example.todoapp.service.ITodoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements ITodoService {
    private final ITodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    @Override
    public void addTodo(TodoDTO newTodo) {
        Todo todo = new Todo();
        todo.setContent(newTodo.getContent());
        todo.setDueDate(newTodo.getDueDate());
        todo.setPriority(newTodo.getPriority());
        todo.setStatus(false);

        todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public void updateTodo(TodoDTO newTodo) {
        Todo updateTodo = new Todo();
        updateTodo.setId(newTodo.getId());
        updateTodo.setContent(newTodo.getContent());
        updateTodo.setDueDate(newTodo.getDueDate());
        updateTodo.setPriority(newTodo.getPriority());
        updateTodo.setStatus(newTodo.isStatus());
        todoRepository.save(updateTodo);
    }
}
