package com.github.ksoichiro.todo.java.springboot.repository;

import com.github.ksoichiro.todo.java.springboot.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
