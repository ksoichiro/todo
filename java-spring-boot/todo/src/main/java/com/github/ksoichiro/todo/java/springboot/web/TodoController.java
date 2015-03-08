package com.github.ksoichiro.todo.java.springboot.web;

import com.github.ksoichiro.todo.java.springboot.domain.TodoState;
import com.github.ksoichiro.todo.java.springboot.form.TodoForm;
import com.github.ksoichiro.todo.java.springboot.service.TodoStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("todos")
public class TodoController {
    @Autowired
    private TodoStateService todoStateService;

    @RequestMapping
    public String index(TodoForm form, Model model) {
        List<TodoState> list = todoStateService.findAll();
        model.addAttribute("allTodoStates", list);
        return "todos/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Validated TodoForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/todos";
        }
        return "redirect:/todos";
    }
}
