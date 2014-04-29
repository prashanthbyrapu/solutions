package app.solutions.core.service.references;

import app.solutions.core.service.DocumentTextService;
import app.solutions.core.service.metadata.FieldMetadata;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.model.BaseObject;
import app.solutions.util.ReflectionUtility;
import app.solutions.util.Utility;
import com.google.inject.Inject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niveditha on 19/4/14.
 */
public class ReferenceDocumentServiceImpl implements ReferenceDocumentService {

    Logger logger = Logger.getLogger(ReferenceDocumentServiceImpl.class);

    private DocumentTextService documentTextService;

    @Inject
    public ReferenceDocumentServiceImpl(DocumentTextService documentTextService) {
        this.documentTextService = documentTextService;
    }

    @Override
    public Map<String, Object> updateWithDocumentTexts(BaseObject document) {

        Map<String, Object> result = ReflectionUtility.getMapOfFieldValues(document);

        String collectionName = document.getClass().getSimpleName();

        FieldMetadata fieldMetadata;
        Map<String, String> textMap = new HashMap<String,String>();
        for (String field : result.keySet()) {
            try {
                fieldMetadata = ReflectionUtility.getFieldMetadata(field, collectionName);
                if (fieldMetadata.isReferenced()) {
                    // Only if referenced.
                    Object fieldValue = result.get(field);
                    String documentText = "--No Document Found--";
                    documentText = documentTextService.getText(fieldMetadata.getReferencedCollection(), (String) fieldValue);
                    textMap.put(Utility.getTextField(field) , documentText);
                }
            } catch (Exception e) {
                logger.error("Error :", e);
            }
        }

        if( textMap.size() > 0 ){
            result.putAll(textMap);
        }
        return result;
    }
}
