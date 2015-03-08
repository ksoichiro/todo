package com.github.ksoichiro.todo.java.springboot.repository;

import com.github.ksoichiro.todo.java.springboot.domain.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
