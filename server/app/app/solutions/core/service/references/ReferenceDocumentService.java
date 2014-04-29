package app.solutions.core.service.references;

import app.solutions.model.BaseObject;

import java.util.Map;

/**
 * Created by niveditha on 19/4/14.
 */
public interface ReferenceDocumentService {

    /**
     *  adds a new field <field>_text which will have the foriegn document text.
     * @param document
     * @return
     */
    public Map<String,Object> updateWithDocumentTexts(BaseObject document);
}
