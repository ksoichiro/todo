package com.github.ksoichiro.todo.java.springboot.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TodoForm {
    @NotNull
    @Max(1024)
    private String title;

    @Max(1024)
    private String note;

    @Min(1)
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
