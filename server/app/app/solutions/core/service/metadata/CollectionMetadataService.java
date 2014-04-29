package app.solutions.core.service.metadata;

/**
 * API to retreive metadata of collection
 * Created by niveditha on 12/4/14.
 */
public interface CollectionMetadataService {

    /**
     * Get Field Metadata
     *
     * @param fieldName      - Name of field
     * @param collectionName - collection to which field belongs
     * @return
     */
    public FieldMetadata getFieldMetadata(String fieldName, String collectionName);

    /**
     * Get the metadata of passed in collection
     *
     * @param collectionName - collection of which metadata needs to be returned
     * @return
     */
    public CollectionMetadata getCollectionMetadata(String collectionName);


    /**
     * Change version enabled for passed in collection name
     *
     * @param collectionName
     * @return
     */
    public boolean isChangeVersionsEnabledForCollection(String collectionName);

    /**
     * Is field value is of list
     *
     * @param fieldName
     * @param collectionName
     * @return
     */
    public boolean isFieldTypeList(String fieldName, String collectionName);

    /**
     * Get field Type
     *
     * @param fieldName
     * @param collectionName
     * @return
     */
    public String getFieldType(String fieldName, String collectionName);

    /**
     * is field type is embedded ..
     *
     * @param fieldName
     * @param collectionName
     * @return
     */
    public boolean isFieldTypeEmbedded(String fieldName, String collectionName);

    /**
     * Is indexing allowed for passed in field of specific collection.
     *
     * @param fieldName
     * @param collectionName
     * @return
     */
    public boolean isIndexingAllowedForField(String fieldName, String collectionName);


}
