package com.github.ksoichiro.todo.java.springboot.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class TodoForm {
    @NotEmpty
    @Size(min = 1, max = 1024)
    private String title;

    @Size(min = 0, max = 1024)
    private String note;

    private String todoStateId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTodoStateId() {
        return todoStateId;
    }

    public void setTodoStateId(String todoStateId) {
        this.todoStateId = todoStateId;
    }
}
