package app.solutions.core.facade;

import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;

import java.util.List;

/**
 * this is the core facade class to get the data from database..which contains CRUD + custom queries.
 * <p/>
 * Created by niveditha on 31/12/13.
 */
public class Document {


    /**
     *  Get Document by Id
     * @param collectionName
     * @param id
     * @param clientId
     * @return
     */
    public static BaseObject getById(String collectionName,String id, Integer clientId){

        BaseDAO dao = DAOFactory.getDAO(clientId, collectionName);
        BaseObject obj = dao.get(id);

        return obj;
    }



    /**
     *  Get All Document from Db without any where condition.
     * @param collectionName
     * @return
     */
    public static List<BaseObject> getAllDocuments(String collectionName, Integer clientId){

        BaseDAO dao = DAOFactory.getDAO(clientId, collectionName);
        return dao.getAll();

    }


    /**
     *  Updates  a document.
     * @param document
     * @return
     */
    public static BaseObject update(BaseObject document, Integer clientId) throws ValidationFailedException {
        BaseDAO dao = DAOFactory.getDAO(clientId, document.getClass().getSimpleName());
        return dao.update(document);
    }


    /*
    * Delete a document from mongodb
     */
    public static void delete(BaseObject document, Integer clientId){
        BaseDAO dao = DAOFactory.getDAO(clientId, document.getClass().getSimpleName());
        dao.delete(document.getId().toString());
    }


    /**
     *  Delete a document by id.
     * @param collectionName
     * @param id
     * @param clientId
     */
    public static void delete(String collectionName, String id, Integer clientId){
        BaseDAO dao = DAOFactory.getDAO(clientId, collectionName);
        dao.delete(id);
    }

}
