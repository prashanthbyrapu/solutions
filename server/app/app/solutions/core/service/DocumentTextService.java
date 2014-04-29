package app.solutions.core.service;

import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;

/**
 * Created by niveditha on 13/4/14.
 */
public interface DocumentTextService {

    /**
     *  Get Text of document
     * @param collectionName
     * @param id
     * @return
     * @throws NoCollectionException
     * @throws NoDocumentExists
     */
    public String getText(String collectionName, String id) throws NoCollectionException, NoDocumentExists;
}
