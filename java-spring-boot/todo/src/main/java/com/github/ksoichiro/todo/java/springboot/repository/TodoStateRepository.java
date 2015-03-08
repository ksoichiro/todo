package com.github.ksoichiro.todo.java.springboot.repository;

import com.github.ksoichiro.todo.java.springboot.domain.TodoState;
import org.springframework.data.repository.CrudRepository;

public interface TodoStateRepository extends CrudRepository<TodoState, Long> {
}
