package com.github.ksoichiro.todo.java.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("todos")
public class TodoController {
    @RequestMapping
    public String index() {
        return "todos/index";
    }
}
