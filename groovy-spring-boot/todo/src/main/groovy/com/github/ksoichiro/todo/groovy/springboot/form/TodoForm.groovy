package com.github.ksoichiro.todo.groovy.springboot.form

import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.Size

public class TodoForm {
    @NotEmpty
    @Size(min = 1, max = 1024)
    String title

    @Size(min = 0, max = 1024)
    String note

    String todoStateId
}
