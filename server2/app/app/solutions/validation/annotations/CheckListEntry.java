package app.solutions.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.solutions.validation.impl.ListEntryValidator;
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME )
@Constraint(validatedBy = { ListEntryValidator.class })
@Documented
public @interface CheckListEntry {

	String message() default "List Entry Does Not Exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();
}
