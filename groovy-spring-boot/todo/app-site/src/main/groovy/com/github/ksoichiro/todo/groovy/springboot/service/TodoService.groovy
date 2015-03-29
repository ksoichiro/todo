package com.github.ksoichiro.todo.groovy.springboot.service

import com.github.ksoichiro.todo.groovy.springboot.domain.Todo
import com.github.ksoichiro.todo.groovy.springboot.form.TodoForm
import com.github.ksoichiro.todo.groovy.springboot.form.TodoUpdateForm
import com.github.ksoichiro.todo.groovy.springboot.repository.TodoRepository
import com.github.ksoichiro.todo.groovy.springboot.repository.TodoStateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository
    @Autowired
    TodoStateRepository todoStateRepository

    public List<Todo> findAll(Long userId) {
        def list = []
        todoRepository.findByUserId(userId).each {
            list += it
        }
        list
    }

    public Todo save(TodoForm form, Long userId) {
        def entity = new Todo()
        entity.with {
            title = form.getTitle()
            note = form.getNote()
            ownerType = 0
            it.userId = userId
            groupId = 0L
            createdAt = System.currentTimeMillis()
            updatedAt = System.currentTimeMillis()
            todoState = todoStateRepository.findOne(Long.parseLong(form.getTodoStateId()))
        }
        todoRepository.save(entity)
    }

    public Todo update(TodoUpdateForm form) {
        def id = Long.parseLong(form.getId())
        def entity = todoRepository.findOne(id)
        if (form.getTitle()) {
            entity.setTitle(form.getTitle())
        }
        if (form.getNote()) {
            entity.setNote(form.getNote())
        }
        entity.setUpdatedAt(System.currentTimeMillis())

        todoRepository.save(entity)
    }

    public void delete(Long todoId) {
        todoRepository.delete(todoId)
    }
}
