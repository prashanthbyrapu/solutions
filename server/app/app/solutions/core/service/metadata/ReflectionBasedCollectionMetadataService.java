package app.solutions.core.service.metadata;

import app.solutions.contexts.UserContext;
import app.solutions.core.service.CacheService;
import app.solutions.core.service.cache.CacheType;
import app.solutions.exceptions.NotFoundInCache;
import app.solutions.util.ReflectionUtility;
import app.solutions.util.Utility;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.log4j.Logger;

/**
 * Created by niveditha on 12/4/14.
 */
@Singleton
public class ReflectionBasedCollectionMetadataService implements CollectionMetadataService {

    Logger logger = Logger.getLogger(ReflectionBasedCollectionMetadataService.class);

    private UserContext userContext;

    private CacheService cacheService;

    @Inject
    public ReflectionBasedCollectionMetadataService(UserContext userContext, CacheService cacheService) {
        this.userContext = userContext;
        this.cacheService = cacheService;
    }

    @Override
    public FieldMetadata getFieldMetadata(String fieldName, String collectionName) {


        String cacheKey = cacheService.generateCacheKey(collectionName, fieldName, CacheType.METADATA_CACHE);
        FieldMetadata fieldMetadata = null;
        try {
            fieldMetadata = (FieldMetadata) cacheService.getByCacheKey(cacheKey);
        } catch (NotFoundInCache notFoundInCache) {
            logger.debug("Not found in cache so getting it from class and pouplating the cache");
            fieldMetadata = ReflectionUtility.getFieldMetadata(fieldName, collectionName);
            cacheService.cachePut(cacheKey, fieldMetadata);
        }


        return fieldMetadata;
    }

    @Override
    public CollectionMetadata getCollectionMetadata(String collectionName) {

        String cacheKey = cacheService.generateCacheKey(collectionName, "", CacheType.METADATA_CACHE);
        CollectionMetadata collectionMetadata = null;
        try {
            collectionMetadata = (CollectionMetadata) cacheService.getByCacheKey(cacheKey);
        } catch (NotFoundInCache notFoundInCache) {
            logger.debug("Not found in cache so getting it from class and pouplating the cache");
            collectionMetadata = ReflectionUtility.getCollectionMetadata(collectionName);
            cacheService.cachePut(cacheKey, collectionMetadata);
        }

        return collectionMetadata;
    }

    @Override
    public boolean isChangeVersionsEnabledForCollection(String collectionName) {
        CollectionMetadata collectionMetadata = getCollectionMetadata(collectionName);
        return collectionMetadata.isChangeVersionEnabled();
    }

    @Override
    public boolean isFieldTypeList(String fieldName, String collectionName) {

        FieldMetadata fieldMetadata = getFieldMetadata(fieldName, collectionName);
        return fieldMetadata.isListType();
    }

    @Override
    public String getFieldType(String fieldName, String collectionName) {
        FieldMetadata fieldMetadata = getFieldMetadata(fieldName, collectionName);
        return fieldMetadata.getFieldType();
    }

    @Override
    public boolean isFieldTypeEmbedded(String fieldName, String collectionName) {
        FieldMetadata fieldMetadata = getFieldMetadata(fieldName, collectionName);
        return fieldMetadata.isEmbeddedType();
    }

    @Override
    public boolean isIndexingAllowedForField(String fieldName, String collectionName) {
        FieldMetadata fieldMetadata = getFieldMetadata(fieldName, collectionName);
        return fieldMetadata.isIndexingAllowed();
    }
}
