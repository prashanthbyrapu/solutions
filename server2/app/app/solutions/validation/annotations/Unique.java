package app.solutions.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.solutions.validation.impl.ListEntryValidator;
import app.solutions.validation.impl.UniqueValidator;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME )
@Constraint(validatedBy = UniqueValidator.class)
@Documented

public @interface Unique {

	String message() default "Not Unique";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
