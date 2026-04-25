package org.example.todoapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.todoapp.dto.TodoDTO;
import org.example.todoapp.service.ITodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TodoController {
    private final ITodoService todoService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodo());
        return "todo";
    }

    @GetMapping("/add")
    public String viewAdd(Model model){
        model.addAttribute("addtodo", new TodoDTO());
        return "addTodo";
    }


    @PostMapping("/add")
    public String addTodo(
            @Valid
            @ModelAttribute(name = "addtodo") TodoDTO newTodo,
            BindingResult result

    ) {
        if (result.hasErrors()) {
            return "addTodo";
        }
        todoService.addTodo(newTodo);
        return "redirect:/";
    }

}
