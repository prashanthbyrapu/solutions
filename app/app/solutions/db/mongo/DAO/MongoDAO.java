package app.solutions.db.mongo.DAO;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.query.Query;

import app.solutions.dao.AbstractDAOImpl;
import app.solutions.dao.BaseDAO;
import app.solutions.db.mongo.MongoConnection;
import app.solutions.exceptions.DBConnectionFailureException;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.model.BaseObject;

public class MongoDAO extends AbstractDAOImpl {

    private Logger logger = Logger.getLogger(MongoDAO.class);

    private String clientId;

    private Morphia morphia;

    private Datastore dataStore;

    protected Class collectionClass;

    protected Class getClassName(String collectionName)
	    throws ClassNotFoundException {
	String className = "app.solutions.model." + collectionName;

	Class collectionClass = Class.forName(className);

	return collectionClass;
    }

    @Override
    public BaseObject get(String id) {
	Query query = dataStore.find(collectionClass, "id", id);
	return (BaseObject) dataStore.find(collectionClass, "id = ", id).get();
	

    }

    @Override
    public List<BaseObject> getAll() {

	return dataStore.find(collectionClass).asList();

    }

   

    @Override
    public boolean delete(String id) {
	
	dataStore.delete(collectionClass, id);
	// TODO Auto-generated method stub
	return true;
    }

   

    @Override
    public BaseObject getByExternalId(String externalId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<BaseObject> getByFieldValue(String fieldName, String value) {
	// TODO Auto-generated method stub
	return null;
    }

    public MongoDAO(String clientId, String collectionName)
	    throws DBConnectionFailureException, NoCollectionException {
	this.clientId = clientId;

	try {
	    collectionClass = getClassName(collectionName);
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    logger.error("Class Not Found ", e);
	    throw new NoCollectionException();
	}

	morphia = new Morphia();

	dataStore = morphia.createDatastore(MongoConnection.getMongo(),
		"solutions");
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
