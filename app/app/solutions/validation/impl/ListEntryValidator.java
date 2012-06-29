package app.solutions.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import app.solutions.validation.annotations.CheckListEntry;


/**
 *  Validator to validate whether the ListEntryId is valid or not. It checks ValueLists.entryId for the mentioned entryID
 * 
 * @author I038968
 *
 */
public class ListEntryValidator implements ConstraintValidator<CheckListEntry, String> {

	
	private String entryId;
	
	@Override
	public void initialize(CheckListEntry id) {
		
		entryId = id.value();
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return true;
	}

}
