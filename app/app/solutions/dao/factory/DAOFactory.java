package app.solutions.dao.factory;

import org.apache.log4j.Logger;

import app.solutions.dao.BaseDAO;
import app.solutions.db.mongo.DAO.MongoDAO;
import app.solutions.exceptions.DBConnectionFailureException;
import app.solutions.exceptions.NoCollectionException;

public class DAOFactory {

    static Logger logger = Logger.getLogger(DAOFactory.class);

    public static BaseDAO getDAO(String collectionName) throws RuntimeException {

	try {
	    return new MongoDAO("", collectionName);
	} catch (DBConnectionFailureException e) {

	    logger.error("DB error", e);
	    throw new RuntimeException("Connection to DB Failed");

	} catch (NoCollectionException e) {
	    logger.error(e);
	    throw new RuntimeException("Collection Not Found");
	}

    }

}
