package com.github.ksoichiro.todo.java.springboot.service;

import com.github.ksoichiro.todo.java.springboot.domain.Todo;
import com.github.ksoichiro.todo.java.springboot.form.TodoForm;
import com.github.ksoichiro.todo.java.springboot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Todo save(TodoForm form) {
        Todo entity = new Todo();
        entity.setTitle(form.getTitle());
        entity.setNote(form.getNote());
        entity.setOwnerType(0);
        entity.setGroupId(0L);
        entity.setCreatedAt(System.currentTimeMillis());
        entity.setUpdatedAt(System.currentTimeMillis());
        entity.setTodoStateId(Long.parseLong(form.getTodoStateId()));

        return todoRepository.save(entity);
    }
}
