package com.github.ksoichiro.todo.java.springboot.service;

import com.github.ksoichiro.todo.java.springboot.domain.Todo;
import com.github.ksoichiro.todo.java.springboot.form.TodoForm;
import com.github.ksoichiro.todo.java.springboot.repository.TodoRepository;
import com.github.ksoichiro.todo.java.springboot.repository.TodoStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoStateRepository todoStateRepository;

    public List<Todo> findAll(Long userId) {
        List<Todo> list = new ArrayList<Todo>();
        for (Todo entity : todoRepository.findByUserId(userId)) {
            list.add(entity);
        }
        return list;
    }

    public Todo save(TodoForm form, Long userId) {
        Todo entity = new Todo();
        entity.setTitle(form.getTitle());
        entity.setNote(form.getNote());
        entity.setOwnerType(0);
        entity.setUserId(userId);
        entity.setGroupId(0L);
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setUpdatedAt(System.currentTimeMillis());
        entity.setTodoState(todoStateRepository.findOne(Long.parseLong(form.getTodoStateId())));

        return todoRepository.save(entity);
    }
}
