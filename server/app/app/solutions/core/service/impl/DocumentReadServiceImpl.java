package app.solutions.core.service.impl;

import app.solutions.contexts.UserContext;
import app.solutions.core.facade.QueryInterface;
import app.solutions.core.service.CacheService;
import app.solutions.core.service.DocumentReadService;
import app.solutions.core.service.metadata.CollectionMetadataService;
import app.solutions.core.service.references.ReferenceDocumentService;
import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.CollectionNotCached;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.exceptions.NotFoundInCache;
import app.solutions.model.BaseObject;
import app.solutions.util.ReflectionUtility;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niveditha on 12/4/14.
 */
public class DocumentReadServiceImpl implements DocumentReadService {


    private UserContext userContext;

    private CollectionMetadataService metadataService;

    private CacheService cacheService;

    private ReferenceDocumentService referenceDocumentService;

    private Object readFromCache(String collectionName, String id) throws NotFoundInCache, CollectionNotCached {

        return cacheService.getByCacheKey(id);

    }

    @Inject
    public DocumentReadServiceImpl(UserContext userContext, CollectionMetadataService metadataService, CacheService cacheService, ReferenceDocumentService refService) {
        this.userContext = userContext;
        this.metadataService = metadataService;
        this.cacheService = cacheService;
        this.referenceDocumentService = refService;
    }


    @Override
    public BaseObject byId(String collectionName, String id) throws NoCollectionException, NoDocumentExists {
        return byId(collectionName, id, null);
    }

    @Override
    public BaseObject byId(String collectionName, String id, List<String> fields) throws NoCollectionException, NoDocumentExists {
        BaseObject document = null;
        try {
            document = (BaseObject) readFromCache(collectionName, id);

        } catch (NotFoundInCache notFoundInCache) {
            BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);
            document = dao.get(id, fields);
        } catch (CollectionNotCached collectionNotCached) {
            BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);
            document = dao.get(id, fields);
        }
        return document;
    }

    @Override
    public Map<String, Object> getFieldValuesMapById(String collectionName, String id, List<String> fields) throws NoCollectionException, NoDocumentExists {

        BaseObject document = byId(collectionName, id, fields);
        return ReflectionUtility.getMapOfFieldValues(document);

    }

    @Override
    public Map<String, Object> getFieldValuesMapById(String collectionName, String id, List<String> fields, boolean readReferenceDocText) throws NoCollectionException, NoDocumentExists {
        BaseObject document = byId(collectionName, id, fields);
        Map<String, Object> result = ReflectionUtility.getMapOfFieldValues(document);

        // now loop through each field and find out whether they are refering to any other document.


        return result;
    }

    @Override
    public Map<String, Object> executeQuery(QueryInterface queryInterface) throws NoCollectionException, NoDocumentExists {
        throw new IllegalStateException("NOt yet Supported in the current version API");
    }

    @Override
    public List<BaseObject> getAll(String collectionName) throws NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);
        return dao.getAll();
    }

    @Override
    public List<Map<String, Object>> getAll(String collectionName, List<String> fields, boolean readReferenceText) throws NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);

        List<BaseObject> result = dao.getAll(fields);
        List<Map<String,Object>> response = new ArrayList<Map<String,Object>>();
       for( BaseObject obj: result ){
            response.add(referenceDocumentService.updateWithDocumentTexts(obj));
       }


        return response;
    }

    @Override
    public List<BaseObject> getByFieldValue(String collectionName, String field, Object fieldValue) throws NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);

        return dao.getByFieldValue(field, fieldValue);

    }
}
