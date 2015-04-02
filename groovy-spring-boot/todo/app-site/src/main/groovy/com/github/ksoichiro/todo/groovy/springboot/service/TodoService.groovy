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

    public boolean ownedBy(Long id, Long userId) {
        if (id == null || userId == null) {
            return false
        }
        return todoRepository.findOne(id)?.userId?.equals(userId);
    }

    public Todo save(TodoForm form, Long userId) {
        def entity = new Todo()
        entity.with {
            title = form.title
            note = form.note
            ownerType = 0
            it.userId = userId
            groupId = 0L
            createdAt = System.currentTimeMillis()
            updatedAt = System.currentTimeMillis()
            todoState = todoStateRepository.findOne(Long.parseLong(form.todoStateId))
        }
        todoRepository.save(entity)
    }

    public Todo update(TodoUpdateForm form) {
        def id = Long.parseLong(form.id)
        def entity = todoRepository.findOne(id)
        if (form.title) {
            entity.setTitle(form.getTitle())
        }
        if (form.note) {
            entity.setNote(form.note)
        }
        entity.setUpdatedAt(System.currentTimeMillis())

        todoRepository.save(entity)
    }

    public void delete(Long todoId) {
        todoRepository.delete(todoId)
    }
}
