package com.github.ksoichiro.todo.java.springboot.validator;

import com.github.ksoichiro.todo.java.springboot.domain.User;
import com.github.ksoichiro.todo.java.springboot.form.PasswordUpdateForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordMatchValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PasswordUpdateForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordUpdateForm form = (PasswordUpdateForm) target;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!new StandardPasswordEncoder().matches(form.getCurrentPassword(), user.getPassword())) {
            errors.rejectValue("currentPassword", "error.wrongPassword");
        }
        if (!form.getNewPassword().equals(form.getNewPasswordConfirm())) {
            errors.rejectValue("newPasswordConfirm", "error.unmatched");
        }
    }
}
