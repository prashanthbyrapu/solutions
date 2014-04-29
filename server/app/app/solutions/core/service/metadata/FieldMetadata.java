package app.solutions.core.service.metadata;

/**
 * Created by niveditha on 17/4/14.
 */
public class FieldMetadata {





    // If field value is list or not
    private boolean isListType;

    // IS field embedded
    private boolean isEmbeddedType;

    /**
     *  Type of the field.. String, double, Address etc..
     */
    private String fieldType;

    /**
     *  Indexing allowed for this field?
     */

    private boolean isIndexingAllowed;

    /**
     *  Is field required
     */
    private boolean isRequired;

    /**
     *  Is field referenced any other collection? Foriegn Key?
     */
    private boolean isReferenced;

    /**
     *  Collection name to which field is refering to?
     */
    private String referencedCollection;

    /**
     *  Field Name to which field is refering to?
     */
    private String referencedField;


    public boolean isListType() {
        return isListType;
    }

    public void setListType(boolean isListType) {
        this.isListType = isListType;
    }

    public boolean isEmbeddedType() {
        return isEmbeddedType;
    }

    public void setEmbeddedType(boolean isEmbeddedType) {
        this.isEmbeddedType = isEmbeddedType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isIndexingAllowed() {
        return isIndexingAllowed;
    }

    public void setIndexingAllowed(boolean isIndexingAllowed) {
        this.isIndexingAllowed = isIndexingAllowed;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public boolean isReferenced() {
        return isReferenced;
    }

    public void setReferenced(boolean isReferenced) {
        this.isReferenced = isReferenced;
    }

    public String getReferencedCollection() {
        return referencedCollection;
    }

    public void setReferencedCollection(String referencedCollection) {
        this.referencedCollection = referencedCollection;
    }

    public String getReferencedField() {
        return referencedField;
    }

    public void setReferencedField(String referencedField) {
        this.referencedField = referencedField;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

}
