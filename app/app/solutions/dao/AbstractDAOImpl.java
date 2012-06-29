package app.solutions.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;

public abstract class AbstractDAOImpl implements BaseDAO {

    @Override
    public BaseObject update(BaseObject document) throws ValidationFailedException {

	validate(document);
	return updateToDataStore(document);
    }

    @Override
    public BaseObject insert(BaseObject document)
	    throws ValidationFailedException {

	validate(document);
	
	return insertToDataStore(document);
    }

    protected void validate(BaseObject document)
	    throws ValidationFailedException {
	
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	Set<ConstraintViolation<BaseObject>> constraintViolations = validator
		.validate(document);

	List<String> errorMessages = new ArrayList<String>();
	for (ConstraintViolation<BaseObject> violation : constraintViolations) {
	    errorMessages.add(violation.getMessage());
	}

	if (!errorMessages.isEmpty()) {
	    throw new ValidationFailedException(errorMessages);
	}
    }

    public abstract BaseObject insertToDataStore(BaseObject document);
    
    
    public abstract BaseObject updateToDataStore(BaseObject document);

}
