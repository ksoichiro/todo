package com.github.ksoichiro.todo.groovy.springboot.web

import com.github.ksoichiro.todo.groovy.springboot.domain.User
import com.github.ksoichiro.todo.groovy.springboot.form.TodoForm
import com.github.ksoichiro.todo.groovy.springboot.form.TodoUpdateForm
import com.github.ksoichiro.todo.groovy.springboot.service.TodoService
import com.github.ksoichiro.todo.groovy.springboot.service.TodoStateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("todos")
public class TodoController {
    @Autowired
    TodoStateService todoStateService
    @Autowired
    TodoService todoService

    @RequestMapping
    public String index(@AuthenticationPrincipal User user, TodoForm form, Model model) {
        model.addAttribute("allTodoStates", todoStateService.findAll())
        model.addAttribute("allTodos", todoService.findAll(user.id))
        "todos/index"
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@AuthenticationPrincipal User user, @Validated TodoForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return index(user, form, model)
        }
        todoService.save(form, user.id)
        "redirect:/todos"
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST, consumes = ["application/json;charset=UTF-8"], produces=["application/json;charset=UTF-8"])
    @ResponseBody
    public String update(@AuthenticationPrincipal User user, @RequestBody TodoUpdateForm form) {
        if (todoService.ownedBy(form.id.toLong(), user.id)) {
            todoService.update(form)
        }
        "{\"result\": 0}"
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String delete(@AuthenticationPrincipal User user, TodoForm form, Model model, @PathVariable Long id) {
        if (todoService.ownedBy(id, user.id)) {
            todoService.delete(id)
        }
        "redirect:/todos"
    }
}
