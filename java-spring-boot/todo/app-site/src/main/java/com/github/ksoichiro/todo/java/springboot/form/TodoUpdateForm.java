package com.github.ksoichiro.todo.java.springboot.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class TodoUpdateForm extends TodoForm {
    @NotNull
    private String id;
}
