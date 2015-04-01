package com.github.ksoichiro.todo.java.springboot.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private Integer ownerType;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt;

    @ManyToOne
    @JoinColumn(name = "todo_state_id")
    private TodoState todoState;
}
