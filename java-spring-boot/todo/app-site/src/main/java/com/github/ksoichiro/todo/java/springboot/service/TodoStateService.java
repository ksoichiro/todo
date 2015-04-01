package com.github.ksoichiro.todo.java.springboot.service;

import com.github.ksoichiro.todo.java.springboot.domain.TodoState;
import com.github.ksoichiro.todo.java.springboot.repository.TodoStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoStateService {
    @Autowired
    private TodoStateRepository todoStateRepository;

    public List<TodoState> findAll() {
        List<TodoState> list = new ArrayList<TodoState>();
        for (TodoState todoState : todoStateRepository.findAll()) {
            list.add(todoState);
        }
        return list;
    }
}
