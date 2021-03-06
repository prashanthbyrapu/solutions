package app.solutions.db.mongo.DAO;

import java.util.List;


import app.solutions.db.mongo.core.MongoStore;


import app.solutions.dao.AbstractDAOImpl;
import app.solutions.db.mongo.core.MongoConnection;
import app.solutions.exceptions.DBConnectionFailureException;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.model.BaseObject;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 * d
 * DAO Implementation for MONGO DB Access
 */
public class MongoDAO extends AbstractDAOImpl {

    private Logger logger = Logger.getLogger(MongoDAO.class);

    private String clientId;

    private Morphia morphia;

    private Datastore dataStore;

    protected Class collectionClass;


    /**
     * Get Class instance of model ..
     *
     * @param collectionName
     * @return
     * @throws ClassNotFoundException
     */
    protected Class getClassName(String collectionName)
            throws ClassNotFoundException {
        String className = "app.solutions.model." + collectionName;

        Class collectionClass = Class.forName(className);

        return collectionClass;
    }

    @Override
    public BaseObject get(String id) {
        // Query query = dataStore.find(collectionClass, "id", id);
        return (BaseObject) dataStore.find(collectionClass, "_id = ", new ObjectId(id)).get();


    }

    @Override
    public List<BaseObject> getAll(List<String> fields) {
        String[] fieldsArray  = new String[fields.size()];
        return dataStore.find(collectionClass).retrievedFields(true, fields.toArray(fieldsArray)).asList();
    }

    @Override
    public List<BaseObject> getAll() {

        return dataStore.find(collectionClass).asList();

    }


    @Override
    public boolean delete(String id) {

        dataStore.delete(collectionClass, id);
        return true;
    }


    @Override
    public BaseObject getByExternalId(String externalId) {
        List<BaseObject> documentsList = getByFieldValue("externalId", externalId);
        if (documentsList.size() > 0) {
            return documentsList.get(0);
        }

        return null;
    }

    @Override
    public List<BaseObject> getByFieldValue(String fieldName, Object value) {
        return dataStore.find(collectionClass, "fieldName = ", value).asList();
    }

    @Override
    public BaseObject get(String id, List<String> fields) {
        if( fields == null || fields.size() == 0){
            return get(id);
        }else{
            String[] fieldsArray = new String[fields.size()];
            BaseObject document = (BaseObject) dataStore.find(collectionClass, "_id = ", new ObjectId(id)).retrievedFields(true, fields.toArray(fieldsArray)).get();
            return document;
        }

    }

    public MongoDAO(String clientId, String collectionName)
            throws DBConnectionFailureException, NoCollectionException {

        this.clientId = clientId;

        try {
            collectionClass = getClassName(collectionName);
        } catch (ClassNotFoundException e) {
            logger.error("Class Not Found ", e);
            throw new NoCollectionException("Collection Not found:" + collectionName);
        }


        dataStore = MongoStore.getInstance().createDataStore(clientId);
    }

    @Override
    public BaseObject insertToDataStore(BaseObject document) {
        dataStore.save(document);
        return document;
    }

    @Override
    public BaseObject updateToDataStore(BaseObject document) {
        dataStore.save(document);
        return document;
    }

}
