package org.example.todoapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.todoapp.dto.TodoDTO;
import org.example.todoapp.model.Todo;
import org.example.todoapp.service.ITodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/todos/edit/{id}")
    public String editTodo(
            @PathVariable(name = "id") Long id,
            Model model
    ){
        for (Todo t: todoService.getAllTodo()) {
            if (t.getId().equals(id)) {
                model.addAttribute("editTodo", t);
            }
        }
        return "updatetodo";
    }

    @PostMapping("/todos/update")
    public String updateTodo(
            @Valid
            @ModelAttribute(name ="editTodo") TodoDTO todoDTO,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ){
        if (result.hasErrors()) {
            return "updatetodo";
        }
        System.out.println(todoDTO.getId());
        todoService.updateTodo(todoDTO);
        redirectAttributes.addFlashAttribute("message","Thao tác thành công!");
        return "redirect:/";
    }

    @GetMapping("/todos/delete/{id}")
    public String deleteTodo(
            @PathVariable(name="id") Long id,
            RedirectAttributes redirectAttributes
    ){
        redirectAttributes.addFlashAttribute("message","Thao tác thành công!");
        todoService.deleteTodo(id);
        return "redirect:/";
    }
}
