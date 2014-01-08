package app.solutions.db.mongo.core;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;

import app.solutions.exceptions.DBConnectionFailureException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 * Mongo  singleton holder
 *
 * @author I038968
 */
public class MongoConnection {

    static Logger logger = Logger.getLogger(MongoConnection.class);


    private static MongoClient mongoClient;

    /**
     * Get Mongo Instance
     *
     * @return
     * @throws DBConnectionFailureException
     */
    public static MongoClient getMongoClient() throws DBConnectionFailureException {

        if (mongoClient == null) {
            synchronized (MongoConnection.class) {
                if (mongoClient == null) {
                    try {
                        mongoClient = new MongoClient("localhost", 27017);
                    } catch (UnknownHostException e) {
                        logger.error("error while connecting to Mongo", e);
                        throw new DBConnectionFailureException();
                    } catch (MongoException e) {
                        logger.error("error while connecting to Mongo", e);
                        throw new DBConnectionFailureException();
                    }
                }
            }
        }


        return mongoClient;
    }

}
