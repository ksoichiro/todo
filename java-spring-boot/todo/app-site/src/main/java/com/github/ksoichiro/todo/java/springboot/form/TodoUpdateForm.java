package com.github.ksoichiro.todo.java.springboot.form;

import javax.validation.constraints.NotNull;

public class TodoUpdateForm extends TodoForm {
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
