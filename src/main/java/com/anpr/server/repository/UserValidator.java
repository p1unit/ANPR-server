package com.anpr.server.repository;

import com.anpr.server.model.User;
import com.anpr.server.resorces.Resources;
import com.anpr.server.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() > 32 ) {
            errors.rejectValue("username", Resources.USERNAME_IS_LARGE);

        }

        Optional<User> userExist = userRepository.findByUsername(user.getUsername());

        if (userExist.isPresent()) {
            errors.reject("username", Resources.DUPLICATE_USER);
        }


        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.reject("password", Resources.PASSWORD_LENGTH_NOT_MATCHED);
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.reject("confirmPassword", Resources.CONFIRM_PASSWORD_ERROR);
        }
    }
}