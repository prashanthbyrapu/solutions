package app.solutions.model;

import app.solutions.model.annotations.NoIndexing;
import app.solutions.validation.annotations.Reference;
import org.mongodb.morphia.annotations.Embedded;

import java.util.List;

/**
 * Created by niveditha on 19/4/14.

 */
public class TestModelA extends BaseObject {


    private String name;

    private List<Integer> integerListField;

    @Embedded
    private TestEmbeddedModelA embeddedField;

    @NoIndexing
    private String indexingNotAllowed;

    @Reference(collectionName = "TestModelB" , fieldName = "id")
    private String referenceField;


    public List<Integer> getIntegerListField() {
        return integerListField;
    }

    public void setIntegerListField(List<Integer> integerListField) {
        this.integerListField = integerListField;
    }

    public TestEmbeddedModelA getEmbeddedField() {
        return embeddedField;
    }

    public void setEmbeddedField(TestEmbeddedModelA embeddedField) {
        this.embeddedField = embeddedField;
    }

    public String getIndexingNotAllowed() {
        return indexingNotAllowed;
    }

    public void setIndexingNotAllowed(String indexingNotAllowed) {
        this.indexingNotAllowed = indexingNotAllowed;
    }

    public String getReferenceField() {
        return referenceField;
    }

    public void setReferenceField(String referenceField) {
        this.referenceField = referenceField;
    }

    @Override
    public String getText(){
        return name;
    }
}
