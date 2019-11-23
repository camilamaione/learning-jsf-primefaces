package model.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameNotExistsValidator.class)
public @interface UsernameNotExists {
	String message() default "Username already exists";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
