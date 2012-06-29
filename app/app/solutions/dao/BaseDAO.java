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
    
    
    
    public BaseObject getByExternalId(String externalId);
    
    
    public List<BaseObject> getByFieldValue(String fieldName, String value);

}
