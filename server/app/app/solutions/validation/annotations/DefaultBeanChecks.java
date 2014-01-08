package app.solutions.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import app.solutions.validation.impl.DefaultBeanValidator;
import app.solutions.validation.impl.ListEntryValidator;


@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME )
@Constraint(validatedBy = DefaultBeanValidator.class)
@Documented
public @interface DefaultBeanChecks {
    
    	String message() default "Default Checks Failed";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String value();
}
