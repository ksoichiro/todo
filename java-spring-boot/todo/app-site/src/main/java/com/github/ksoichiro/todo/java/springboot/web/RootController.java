package com.github.ksoichiro.todo.java.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/todos";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
