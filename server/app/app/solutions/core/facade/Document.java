package app.solutions.core.facade;

import app.solutions.contexts.UserContext;
import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;

import java.util.List;

/**
 * this is the core facade class to get the data from database..which contains CRUD + custom queries.
 * <p/>
 * Created by niveditha on 31/12/13.
 */
public class Document {


    public static BaseObject insert(BaseObject document, UserContext userContext) throws ValidationFailedException, NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, document.getClass().getSimpleName());

        return dao.insert(document);


    }

    /**
     * Get Document by Id
     *
     * @param collectionName
     * @param id
     * @param clientId
     * @return
     */
    public static BaseObject getById(String collectionName, String id, UserContext userContext) throws NoCollectionException {

        BaseDAO dao = null;

        dao = DAOFactory.getDAO(userContext.client, collectionName);

        BaseObject obj = dao.get(id);

        return obj;
    }


    /**
     * Get Document by Id
     *
     * @param collectionName
     * @param id
     * @param clientId
     * @return
     */
    public static BaseObject getById(String collectionName, String id, List<String> fields, UserContext userContext) throws NoCollectionException {

        BaseDAO dao = null;

        dao = DAOFactory.getDAO(userContext.client, collectionName);

        BaseObject obj = dao.get(id,fields);

        return obj;
    }


    /**
     * Get All Document from Db without any where condition.
     *
     * @param collectionName
     * @return
     */
    public static List<BaseObject> getAllDocuments(String collectionName, UserContext userContext) throws NoCollectionException {

        BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);
        return dao.getAll();

    }


    /**
     * Updates  a document.
     *
     * @param document
     * @return
     */
    public static BaseObject update(BaseObject document, UserContext userContext) throws ValidationFailedException, NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, document.getClass().getSimpleName());
        return dao.update(document);
    }


    /*
    * Delete a document from mongodb
     */
    public static void delete(BaseObject document, UserContext userContext) throws NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, document.getClass().getSimpleName());
        dao.delete(document.getId().toString());
    }


    /**
     * Delete a document by id.
     *
     * @param collectionName
     * @param id
     */
    public static void delete(String collectionName, String id, UserContext userContext) throws NoCollectionException {
        BaseDAO dao = DAOFactory.getDAO(userContext.client, collectionName);
        dao.delete(id);
    }

}
