package com.github.ksoichiro.todo.groovy.springboot.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
public class Role {
    @Id
    Long id
    String name
}
