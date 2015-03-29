package com.github.ksoichiro.todo.groovy.springboot.service

import com.github.ksoichiro.todo.groovy.springboot.domain.TodoState
import com.github.ksoichiro.todo.groovy.springboot.repository.TodoStateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class TodoStateService {
    @Autowired
    TodoStateRepository todoStateRepository

    public List<TodoState> findAll() {
        def list = []
        todoStateRepository.findAll().each {
            list += it
        }
        list
    }
}
