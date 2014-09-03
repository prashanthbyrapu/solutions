package app.solutions.core.service;

import app.solutions.core.service.beans.DocumentTextBean;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;

import java.util.List;
import java.util.Map;

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


    public List<DocumentTextBean> getTexts(String collectionName) throws NoCollectionException;
}
