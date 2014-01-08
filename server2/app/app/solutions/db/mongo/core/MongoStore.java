package app.solutions.db.mongo.core;

import app.solutions.exceptions.DBConnectionFailureException;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.DatastoreService;
import org.mongodb.morphia.Morphia;

/**
 * Created by niveditha on 1/1/14.
 */
public class MongoStore {

    private static MongoStore mongoStore = null;

    private MongoClient mongo = null;

    private Morphia morphia = null;

    // Constructor
    private MongoStore() throws DBConnectionFailureException {

         // Get Mongo Client
         mongo = MongoConnection.getMongoClient();

         //Morphia
         morphia = new Morphia();

         // Map all the classes of model package.
         morphia.mapPackage("solutions.model");


    }


    /**
     *  Get MongoStore to operate on Mongo
     * @return
     * @throws DBConnectionFailureException
     */
    public static MongoStore getInstance() throws DBConnectionFailureException {

        if( mongoStore == null ){
            mongoStore = new MongoStore();
        }

        return mongoStore;
    }

    /**
     *  Create Datastore.
     * @param dbName
     * @return
     */
    public Datastore createDataStore(String dbName ){
        return morphia.createDatastore(mongo, dbName);
    }
}
