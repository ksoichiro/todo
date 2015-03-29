package com.github.ksoichiro.todo.groovy.springboot.form;

import javax.validation.constraints.NotNull;

public class TodoUpdateForm extends TodoForm {
    @NotNull
    String id
}
