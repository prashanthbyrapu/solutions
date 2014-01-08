package app.solutions.validation;

import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by niveditha on 2/1/14.
 */
public class DocumentValidator {

    /**
     *  Validate the document data ..currently uses hibernate validator framework.
     * @param document
     * @throws ValidationFailedException
     */
    public static void validate(BaseObject document) throws ValidationFailedException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<BaseObject>> constraintViolations = validator
                .validate(document);

        List<String> errorMessages = new ArrayList<String>();
        for (ConstraintViolation<BaseObject> violation : constraintViolations) {
            errorMessages.add(violation.getMessage() ); // it works + violation.getPropertyPath().toString()
        }

        if (!errorMessages.isEmpty()) {
            throw new ValidationFailedException(errorMessages);
        }
    }
}
