package com.github.ksoichiro.todo.groovy.springboot.repository

import com.github.ksoichiro.todo.groovy.springboot.domain.Todo
import org.springframework.data.repository.CrudRepository

public interface TodoRepository extends CrudRepository<Todo, Long> {
    Iterable<Todo> findByUserId(Long userId)
}
