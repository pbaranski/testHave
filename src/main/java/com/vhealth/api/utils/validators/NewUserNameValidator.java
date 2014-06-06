package com.vhealth.api.utils.validators;

import com.vhealth.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class NewUserNameValidator implements ConstraintValidator<NewUserName, String> {

    @Autowired
    UserService userService;
    private boolean isUpdate;

    @Override
    public void initialize(NewUserName constraintAnnotation) {
        isUpdate = constraintAnnotation.isUpdate();
    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        //TODO this is basically checked in @NotEmpty validation - but somehow this code is executed even @NotEmpty fails
        if (userName == null || !userName.matches("\\w+")) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("userName shouldn't be empty or contain non alphanumerical symbols")
                    .addConstraintViolation();
            return false;
        }
        if (isUpdate && userService.findByUserName(userName) == null) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Creating user requires unique userName")
                    .addConstraintViolation();
            return false;

        }
        if (!isUpdate && userService.findByUserName(userName) != null) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("Creating user requires unique userName")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
