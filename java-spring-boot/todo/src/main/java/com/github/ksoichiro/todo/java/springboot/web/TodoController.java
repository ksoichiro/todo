package com.github.ksoichiro.todo.java.springboot.web;

import com.github.ksoichiro.todo.java.springboot.domain.User;
import com.github.ksoichiro.todo.java.springboot.form.TodoForm;
import com.github.ksoichiro.todo.java.springboot.service.TodoService;
import com.github.ksoichiro.todo.java.springboot.service.TodoStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("todos")
public class TodoController {
    @Autowired
    private TodoStateService todoStateService;
    @Autowired
    private TodoService todoService;

    @RequestMapping
    public String index(Principal principal, TodoForm form, Model model) {
        model.addAttribute("allTodoStates", todoStateService.findAll());

        Authentication authentication = (Authentication) principal;
        User user = (User) authentication.getPrincipal();
        model.addAttribute("allTodos", todoService.findAll(user.getId()));

        return "todos/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Principal principal, @Validated TodoForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return index(principal, form, model);
        }
        Authentication authentication = (Authentication) principal;
        User user = (User) authentication.getPrincipal();
        todoService.save(form, user.getId());
        return "redirect:/todos";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(Principal principal, TodoForm form, Model model, @PathVariable Long id) {
        todoService.delete(id);
        return "redirect:/todos";
    }
}
