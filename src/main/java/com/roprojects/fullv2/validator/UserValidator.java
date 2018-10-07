package com.roprojects.fullv2.validator;

import com.roprojects.fullv2.entity.User;
import com.roprojects.fullv2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.general.not-empty");
        if (userService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "validation.user.duplicate.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "validation.general.not-empty");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "validation.user.size.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "validation.user.diff.password-confirm");
        }
    }
}
