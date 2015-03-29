package com.github.ksoichiro.todo.groovy.springboot.web

import com.github.ksoichiro.todo.groovy.springboot.domain.User
import com.github.ksoichiro.todo.groovy.springboot.form.TodoForm
import com.github.ksoichiro.todo.groovy.springboot.form.TodoUpdateForm
import com.github.ksoichiro.todo.groovy.springboot.service.TodoService
import com.github.ksoichiro.todo.groovy.springboot.service.TodoStateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import java.security.Principal

@Controller
@RequestMapping("todos")
public class TodoController {
    @Autowired
    TodoStateService todoStateService
    @Autowired
    TodoService todoService

    @RequestMapping
    public String index(Principal principal, TodoForm form, Model model) {
        model.addAttribute("allTodoStates", todoStateService.findAll())

        Authentication authentication = (Authentication) principal
        User user = (User) authentication.getPrincipal()
        model.addAttribute("allTodos", todoService.findAll(user.getId()))

        "todos/index"
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Principal principal, @Validated TodoForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return index(principal, form, model)
        }
        Authentication authentication = (Authentication) principal
        User user = (User) authentication.getPrincipal()
        todoService.save(form, user.getId())
        "redirect:/todos"
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST, consumes = ["application/json;charset=UTF-8"], produces=["application/json;charset=UTF-8"])
    @ResponseBody
    public String update(@RequestBody TodoUpdateForm form) {
        todoService.update(form)
        "{\"result\": 0}"
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(Principal principal, TodoForm form, Model model, @PathVariable Long id) {
        todoService.delete(id)
        "redirect:/todos"
    }
}
