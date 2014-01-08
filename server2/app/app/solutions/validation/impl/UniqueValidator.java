package app.solutions.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.solutions.validation.annotations.CheckCommunication;
import app.solutions.validation.annotations.Unique;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @Override
    public void initialize(Unique arg0) {
	
	
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
	
	return true;
    }

}
