package com.github.ksoichiro.todo.java.springboot.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@Data
public class TodoForm {
    @NotEmpty
    @Size(min = 1, max = 1024)
    private String title;

    @Size(min = 0, max = 1024)
    private String note;

    private String todoStateId;
}
