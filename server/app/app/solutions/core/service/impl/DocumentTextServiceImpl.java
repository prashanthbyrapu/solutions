package app.solutions.core.service.impl;

import app.solutions.contexts.UserContext;
import app.solutions.core.service.CacheService;
import app.solutions.core.service.metadata.CollectionMetadataService;
import app.solutions.core.service.DocumentReadService;
import app.solutions.core.service.DocumentTextService;
import app.solutions.core.service.cache.CacheType;
import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.CollectionNotCached;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.exceptions.NotFoundInCache;
import app.solutions.model.BaseObject;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * Created by niveditha on 13/4/14.
 */
public class DocumentTextServiceImpl implements DocumentTextService {


    private UserContext userContext;

    private CollectionMetadataService metadataService;

    private CacheService cacheService;

    private DocumentReadService readService;

    @Inject
    public DocumentTextServiceImpl(UserContext userContext, CollectionMetadataService metadataService, CacheService cacheService, DocumentReadService docReadService) {
        this.userContext = userContext;
        this.metadataService = metadataService;
        this.cacheService = cacheService;
        this.readService = docReadService;
    }


    private String getTextFromDB(String id, String collectionName) throws NoCollectionException {
        String documentText = "";
        BaseDAO baseDAO = DAOFactory.getDAO(userContext.client, collectionName);
        BaseObject name = baseDAO.get(id);
        if (name != null) {
            documentText = name.getText();
        }

        return documentText;
    }

    @Override
    public String getText(String collectionName, String id) throws NoCollectionException, NoDocumentExists {

        String cacheKey = cacheService.generateCacheKey(collectionName, id, CacheType.DOCUMENT_TEXT_CACHE);
        String documentText = null;
        if (cacheKey != null) {
            try {
                documentText = (String) cacheService.getByCacheKey(cacheKey);
            } catch (NotFoundInCache notFoundInCache) {
                documentText = getTextFromDB(id, collectionName);
                cacheService.cachePut( cacheKey, documentText);
            }
        }

        return documentText;
    }
}
