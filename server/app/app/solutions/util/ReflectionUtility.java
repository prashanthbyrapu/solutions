package app.solutions.util;

import app.solutions.core.service.metadata.CollectionMetadata;
import app.solutions.core.service.metadata.FieldMetadata;
import app.solutions.exceptions.MyRuntimeException;
import app.solutions.model.annotations.ChangeVersionEnabled;
import app.solutions.model.annotations.NoIndexing;
import app.solutions.validation.annotations.Reference;
import com.google.common.reflect.Reflection;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.internal.util.ReflectionHelper;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * Created by niveditha on 12/4/14.
 */
public class ReflectionUtility {


    /**
     * Converts bean to map of field name / Value key pairs.
     *
     * @param document
     * @return
     */
    public static Map<String, Object> getMapOfFieldValues(Object document) {
        try {
            return PropertyUtils.describe(document);
        } catch (Exception e) {
            throw new MyRuntimeException("Internal Error while getting the properties of an object: " + document.getClass().getName());
        }
    }


    /**
     * Get Declared Field definition using reflection.
     *
     * @param fieldName
     * @param collectionName
     * @return
     */
    public static Field getDeclaredField(String fieldName, String collectionName) {
        Class modelClass = null;
        Field field = null;
        try {
            modelClass = Utility.getClassName(collectionName);
            field = modelClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException ex) {
            // try super class.
            String superClass = modelClass.getSuperclass().getSimpleName();
            field = getDeclaredField(fieldName, superClass);
        } catch (Exception e) {
            throw new MyRuntimeException("Error while getting data from collection:" + e.getMessage());
        }

        return field;

    }


    /**
     * @param collectionName
     * @return
     */
    public static CollectionMetadata getCollectionMetadata(String collectionName) {
        CollectionMetadata collectionMetadata = new CollectionMetadata();

        try {
            Class modelClass = Utility.getClassName(collectionName);
            Embedded embeddedAnnotation = (Embedded) modelClass.getAnnotation(Embedded.class);
            if (embeddedAnnotation != null) {
                collectionMetadata.setEmbedded(true);
            }

            ChangeVersionEnabled changeVersionEnabledAnnotation = (ChangeVersionEnabled) modelClass.getAnnotation(ChangeVersionEnabled.class);
            if (changeVersionEnabledAnnotation != null) {
                collectionMetadata.setChangeVersionEnabled(true);
            }
            collectionMetadata.setSuperClass(modelClass.getSuperclass().getSimpleName());
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage());
        }

        return collectionMetadata;
    }


    /**
     * Get FieldMetadata using reflection.
     *
     * @param fieldName
     * @param collectionName
     * @return
     */
    public static FieldMetadata getFieldMetadata(String fieldName, String collectionName) {
        FieldMetadata fieldMetadata = new FieldMetadata();

        Field field = getDeclaredField(fieldName, collectionName);


        if (field.getType().getSimpleName().equals("List")) {
            fieldMetadata.setListType(true);
        }
        Annotation[] annotations = field.getAnnotations();

        Embedded embeddedAnnotation = field.getAnnotation(Embedded.class);
        if (embeddedAnnotation != null) {
            fieldMetadata.setEmbeddedType(true);
        }

        if (fieldMetadata.isListType()) {
            ParameterizedType type = (ParameterizedType) field.getGenericType();
            Class typeClass = (Class) type.getActualTypeArguments()[0];
            fieldMetadata.setFieldType(typeClass.getSimpleName());
        } else {
            fieldMetadata.setFieldType(field.getType().getSimpleName());
        }

        NoIndexing noIndexingAnnotation = field.getAnnotation(NoIndexing.class);
        if (noIndexingAnnotation != null) {
            fieldMetadata.setIndexingAllowed(false);
        } else {
            fieldMetadata.setIndexingAllowed(true);
        }

        Reference referenceAnnotation = field.getAnnotation(Reference.class);
        if( referenceAnnotation != null ){
            fieldMetadata.setReferenced(true);
            fieldMetadata.setReferencedCollection(referenceAnnotation.collectionName());
            fieldMetadata.setReferencedField(referenceAnnotation.fieldName());
        }
        return fieldMetadata;
    }
}
