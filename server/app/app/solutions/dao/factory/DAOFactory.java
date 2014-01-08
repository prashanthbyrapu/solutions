package app.solutions.dao.factory;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.log4j.Logger;

import app.solutions.dao.BaseDAO;
import app.solutions.db.mongo.DAO.MongoDAO;
import app.solutions.exceptions.DBConnectionFailureException;
import app.solutions.exceptions.NoCollectionException;

/**
 *  Factory class for DAO.
 */
public class DAOFactory {

    static Logger logger = Logger.getLogger(DAOFactory.class);

    public static BaseDAO getDAO(Integer clientId, String collectionName) throws RuntimeException, NoCollectionException {

        Preconditions.checkArgument((clientId != null), "Client Can not be empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(collectionName), "Collection can not be null or empty");


        try {
            return new MongoDAO(clientId.toString(), collectionName);
        } catch (DBConnectionFailureException e) {

            logger.error("DB error", e);
            throw new RuntimeException("Connection to DB Failed");

        }

    }

}
