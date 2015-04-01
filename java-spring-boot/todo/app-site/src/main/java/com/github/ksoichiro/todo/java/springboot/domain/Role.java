package com.github.ksoichiro.todo.java.springboot.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Role {
    @Id
    private Long id;
    private String name;
}
