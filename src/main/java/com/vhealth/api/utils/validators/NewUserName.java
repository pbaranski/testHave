package com.vhealth.api.utils.validators;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NewUserNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NewUserName {
    String message() default "{NewUserName}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean isUpdate() default true;
}
