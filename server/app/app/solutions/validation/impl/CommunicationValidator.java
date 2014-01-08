package app.solutions.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import app.solutions.validation.annotations.CheckCommunication;

/**
 *  Communication validator implementation
 * @author I038968
 *
 */

public class CommunicationValidator implements
	ConstraintValidator<CheckCommunication, String> {

    @Override
    public void initialize(CheckCommunication arg0) {
	// TODO Auto-generated method stub

	
    }

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
	// TODO Auto-generated method stub
	return true;
    }

}
