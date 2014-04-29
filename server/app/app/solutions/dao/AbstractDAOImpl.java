package app.solutions.dao;

import java.util.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;
import app.solutions.util.ReflectionUtility;
import app.solutions.validation.DocumentValidator;

public abstract class AbstractDAOImpl implements BaseDAO {

    @Override
    public String getText(String id) {
        String documentText = "";



        return documentText;
    }

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

    /**
     * Thsi will be implemented by specific concrete DAO
     *
     * @param document
     * @return
     */
    public abstract BaseObject insertToDataStore(BaseObject document);


    /**
     * Thsi will be implemented by concrete DAO.
     *
     * @param document
     * @return
     */
    public abstract BaseObject updateToDataStore(BaseObject document);

    /**
     * Get document field-value pairs by document id
     *
     * @param id
     * @param fields
     * @param retreiveReferencedDocText
     * @return
     */
    public Map<String, Object> get(String id, List<String> fields, boolean retreiveReferencedDocText) {
        Map<String, Object> result = new HashMap<String, Object>();
        BaseObject document = get(id, fields);
        if (document != null) {
            result = ReflectionUtility.getMapOfFieldValues(document);
            if (retreiveReferencedDocText) {

            }
        }
        return result;
    }


    /**
     * Get document data..
     *
     * @param id
     * @param retreiveReferencedDocText
     * @return
     */
    public Map<String, Object> get(String id, boolean retreiveReferencedDocText) {
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }


}
