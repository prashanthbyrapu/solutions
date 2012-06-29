package app.solutions.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.solutions.validation.annotations.DefaultBeanChecks;

public class DefaultBeanValidator implements ConstraintValidator<DefaultBeanChecks, Object> {

    @Override
    public void initialize(DefaultBeanChecks arg0) {
	
	
    }

    @Override
    public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
	
	return false;
    }
    
    
    /**
     *  Check Unique Constraint
     * @param value
     * @param collectionName
     * @return
     */
    protected boolean checkUniqueConstraint(String value, String collectionName ){
	
	
	return true;
    }

}
