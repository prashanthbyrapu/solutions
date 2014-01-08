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
import app.solutions.validation.DocumentValidator;

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

        DocumentValidator.validate(document);
    }

    public abstract BaseObject insertToDataStore(BaseObject document);


    public abstract BaseObject updateToDataStore(BaseObject document);

}
