package app.solutions.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationFailedException extends Exception {
    
    List<String> errorMessages = new ArrayList<String>();
    
    
    public void addError(String error){
	errorMessages.add(error);
    }
    
    public List<String> getErrors(){
	return errorMessages;
    }
    
    
    public ValidationFailedException(List<String> errors){
	errorMessages = errors;
    }

}
