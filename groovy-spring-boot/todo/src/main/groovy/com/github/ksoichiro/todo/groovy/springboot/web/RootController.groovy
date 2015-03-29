package com.github.ksoichiro.todo.groovy.springboot.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
public class RootController {
    @RequestMapping("/")
    public String index() {
        "redirect:/todos"
    }

    @RequestMapping("/login")
    public String login() {
        "login"
    }
}
