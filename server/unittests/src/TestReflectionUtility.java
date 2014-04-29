import app.solutions.core.service.metadata.CollectionMetadata;
import app.solutions.core.service.metadata.FieldMetadata;
import app.solutions.exceptions.MyRuntimeException;
import app.solutions.util.ReflectionUtility;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by niveditha on 18/4/14.
 */
public class TestReflectionUtility extends TestBase {

    @Test
    public void testFieldMetadata_SuperClassField(){
        FieldMetadata fieldMetadata = ReflectionUtility.getFieldMetadata("createdBy", "Person");

        assertTrue( fieldMetadata.isIndexingAllowed());
        assertTrue( fieldMetadata.getFieldType().equals("String"));
    }


    @Test
    public void testCollectionMetadata_EntityClass(){
        CollectionMetadata collectionMetadata = ReflectionUtility.getCollectionMetadata("Person");

        assertTrue(collectionMetadata.isChangeVersionEnabled());
        assertTrue(collectionMetadata.getSuperClass().equals("BaseObject"));
    }

    @Test
    public void testGetFieldMetadata_EmbeddedField() {
        FieldMetadata fieldMetadata = app.solutions.util.ReflectionUtility.getFieldMetadata("address", "Person");

        assertTrue(fieldMetadata.getFieldType().equals("Address"));
        assertTrue(fieldMetadata.isEmbeddedType() == true);
        assertTrue(fieldMetadata.isListType() == false);
        assertTrue(fieldMetadata.isIndexingAllowed());

    }

    @Test
    public void testGetFieldMetadata_ListField() {
        FieldMetadata fieldMetadata = ReflectionUtility.getFieldMetadata("identificationMarks", "Person");

        assertTrue(fieldMetadata.getFieldType().equals("String"));
        assertTrue(fieldMetadata.isIndexingAllowed());
        assertTrue(fieldMetadata.isListType());
        assertTrue(fieldMetadata.isEmbeddedType() == false);

    }

    @Test
    public void testGetFieldMetadata_PrimitiveField() {
        FieldMetadata fieldMetadata = ReflectionUtility.getFieldMetadata("dateOfBirth", "Person");

        assertTrue(fieldMetadata.getFieldType().equals("Date"));
        assertTrue(fieldMetadata.isIndexingAllowed());
        assertTrue(fieldMetadata.isListType() == false);
        assertTrue(fieldMetadata.isEmbeddedType() == false);
    }

    @Test
    public void testGetFieldMetadata_WrongFieldName() {
        try {
            FieldMetadata fieldMetadata = ReflectionUtility.getFieldMetadata("prashanthField", "Person");
            assertTrue(false);
        } catch (MyRuntimeException ex) {

        }


    }
}
