package com.github.ksoichiro.todo.java.springboot.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
public class PasswordUpdateForm {
    private static final String PASSWORD_PATTERN = "[a-zA-Z0-9_]+";

    @NotEmpty
    @Pattern(regexp = PASSWORD_PATTERN)
    private String currentPassword;

    @NotEmpty
    @Pattern(regexp = PASSWORD_PATTERN)
    private String newPassword;

    @NotEmpty
    @Pattern(regexp = PASSWORD_PATTERN)
    private String newPasswordConfirm;
}
