package com.techdeveloper.phonebook.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = ValidateEmailImpl.class)
public @interface ValidateEmail {

	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
