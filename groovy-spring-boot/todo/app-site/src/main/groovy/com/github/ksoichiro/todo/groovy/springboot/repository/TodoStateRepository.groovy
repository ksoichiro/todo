package com.github.ksoichiro.todo.groovy.springboot.repository

import com.github.ksoichiro.todo.groovy.springboot.domain.TodoState
import org.springframework.data.repository.CrudRepository

public interface TodoStateRepository extends CrudRepository<TodoState, Long> {
}
