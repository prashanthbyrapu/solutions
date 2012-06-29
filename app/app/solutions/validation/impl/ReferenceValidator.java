package app.solutions.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.solutions.validation.annotations.Reference;

public class ReferenceValidator implements ConstraintValidator<Reference, String>{

    @Override
    public void initialize(Reference arg0) {
	
	
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
	
	return true;
    }

}
