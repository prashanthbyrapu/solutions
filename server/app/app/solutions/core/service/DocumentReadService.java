package app.solutions.core.service;

import app.solutions.contexts.UserContext;
import app.solutions.core.facade.QueryInterface;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.model.BaseObject;

import java.util.List;
import java.util.Map;

/**
 * Created by niveditha on 12/4/14.
 */
public interface DocumentReadService {

    /**
     * @param collectionName
     * @param id
     * @return
     */
    public BaseObject byId(String collectionName, String id) throws NoCollectionException, NoDocumentExists;


    /**
     * @param collectionName
     * @param id
     * @param fields
     * @return
     */
    public BaseObject byId(String collectionName, String id, List<String> fields) throws NoCollectionException, NoDocumentExists;


    /**
     * Get Field Values Map ( Key - > FieldName , Value -> Field value )
     *
     * @param collectionName - Primary Collection Name
     * @param id             - Document Id
     * @param fields         - Field to be retreived
     * @return
     */
    public Map<String, Object> getFieldValuesMapById(String collectionName, String id, List<String> fields) throws NoCollectionException, NoDocumentExists;


    /**
     * Get Field Values Map ( Key - > FieldName , Value -> Field value )
     *
     * @param collectionName       - Primary Collection Name
     * @param id                   - Document Id
     * @param fields               - Field to be retreived
     * @param readReferenceDocText
     * @return
     */
    public Map<String, Object> getFieldValuesMapById(String collectionName, String id, List<String> fields, boolean readReferenceDocText) throws NoCollectionException, NoDocumentExists;


    /**
     * Execute Query
     *
     * @param queryInterface
     * @return
     */
    public Map<String, Object> executeQuery(QueryInterface queryInterface) throws NoCollectionException, NoDocumentExists;


    /*
     Get All Documents from BD
     */
    public List<BaseObject> getAll(String collectionName) throws NoCollectionException;


    /**
     * Get all data of collection..
     *
     *
     * @param collectionName
     * @param fields
     * @param readReferenceText
     * @return
     */
    public List<Map<String, Object>> getAll(String collectionName, List<String> fields, boolean readReferenceText) throws NoCollectionException;


    /**
     *  Get documents by specified field value.
     * @param collectionName
     * @param field
     * @param fieldValue
     * @return
     * @throws NoCollectionException
     */
    public List<BaseObject> getByFieldValue(String collectionName, String field, Object fieldValue) throws NoCollectionException;


}
