package com.github.ksoichiro.todo.groovy.springboot.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
public class TodoState {
    @Id
    Long id

    String name

    String description

    @Column(nullable = false)
    Long createdAt

    @Column(nullable = false)
    Long updatedAt
}
