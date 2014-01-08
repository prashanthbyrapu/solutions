package app.solutions.dao;

import java.util.List;

import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;


/**
 *  Base DAO for all collections.
 * @author I038968
 *
 */
public interface BaseDAO {
    
    /**
     *  Get By ID
     * @param id
     * @return Collection
     */
    public BaseObject get(String id) ;
    
    
    /**
     * Get All Collections
     * @return
     */
    public List<BaseObject> getAll() ;
    
    
    
    /**
     *  Update document
     * @param document
     * @return
     */
    public BaseObject update(BaseObject document) throws ValidationFailedException;
    
    
    /**
     * Delete by Id
     * @param id
     * @return
     */
    public boolean delete(String id);
    
    
    
    /**
     * Insert new document
     * @param document
     * @return
     */
    public BaseObject insert(BaseObject document) throws ValidationFailedException;


    /**
     *  Get document by external id
     * @param externalId
     * @return
     */
    public BaseObject getByExternalId(String externalId);


    /**
     *  Get document by field value of specificed field name
     * @param fieldName
     * @param value
     * @return
     */
    public List<BaseObject> getByFieldValue(String fieldName, String value);


    /**
     *  Get document by id and retreive only requested fields.
     * @param id
     * @param fields
     * @return
     */
    public BaseObject get(String id, List<String> fields);

}
