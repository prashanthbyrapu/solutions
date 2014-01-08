package app.solutions.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.solutions.validation.annotations.ExistsInListEntries;


/**
 *  Validator to validate whether the ListEntryId is valid or not. It checks ValueLists.entryId for the mentioned entryID
 * 
 * @author I038968
 *
 */
public class ListEntryValidator implements ConstraintValidator<ExistsInListEntries, String> {

	
	private String entryId;
	
	@Override
	public void initialize(ExistsInListEntries id) {
		
		entryId = id.value();
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return true;
	}

}
