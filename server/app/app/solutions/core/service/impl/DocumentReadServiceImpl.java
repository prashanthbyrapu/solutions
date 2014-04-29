package app.solutions.core.service.impl;

import app.solutions.contexts.UserContext;
import app.solutions.core.facade.QueryInterface;
import app.solutions.core.service.CacheService;
import app.solutions.core.service.DocumentReadService;
import app.solutions.core.service.metadata.CollectionMetadataService;
import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.CollectionNotCached;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.exceptions.NotFoundInCache;
import app.solutions.model.BaseObject;
import app.solutions.util.ReflectionUtility;
import com.google.inject.Inject;

import java.util.List;
import java.util.Map;

/**
 * Created by niveditha on 12/4/14.
 */
public class DocumentReadServiceImpl implements DocumentReadService {


    private UserContext userContext;

    private CollectionMetadataService metadataService;

    private CacheService cacheService;


    private Object readFromCache(String collectionName, String id) throws NotFoundInCache, CollectionNotCached {

        return cacheService.getByCacheKey(id);

    }

    @Inject
    public DocumentReadServiceImpl(UserContext userContext, CollectionMetadataService metadataService, CacheService cacheService) {
        this.userContext = userContext;
        this.metadataService = metadataService;
        this.cacheService = cacheService;
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

        BaseObject document =  byId(collectionName, id, fields);
        return ReflectionUtility.getMapOfFieldValues(document);

    }

    @Override
    public Map<String, Object> getFieldValuesMapById(String collectionName, String id, List<String> fields, boolean readReferenceDocText) throws NoCollectionException, NoDocumentExists {
        BaseObject document =  byId(collectionName, id, fields);
        Map<String,Object> result =  ReflectionUtility.getMapOfFieldValues(document);

        // now loop through each field and find out whether they are refering to any other document.




        return result;
    }

    @Override
    public Map<String, Object> executeQuery(QueryInterface queryInterface) throws NoCollectionException, NoDocumentExists {
        throw new IllegalStateException("NOt yet Supported in the current version API");
    }
}
