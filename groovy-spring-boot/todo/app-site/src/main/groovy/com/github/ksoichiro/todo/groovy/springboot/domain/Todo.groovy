package com.github.ksoichiro.todo.groovy.springboot.domain

import javax.persistence.*

@Entity
public class Todo {
    @Id
    @GeneratedValue
    Long id

    @Column(nullable = false)
    String title

    @Column(nullable = false)
    String note

    @Column(nullable = false)
    Integer ownerType

    @Column(nullable = false)
    Long userId

    @Column(nullable = false)
    Long groupId

    @Column(nullable = false)
    Long createdAt

    @Column(nullable = false)
    Long updatedAt

    @ManyToOne
    @JoinColumn(name = "todo_state_id")
    TodoState todoState
}
