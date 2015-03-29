package com.github.ksoichiro.todo.groovy.springboot.repository

import com.github.ksoichiro.todo.groovy.springboot.domain.User
import org.springframework.data.repository.CrudRepository

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username)
}
