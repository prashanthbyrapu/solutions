package app.solutions.db.mongo;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import app.solutions.exceptions.DBConnectionFailureException;

import com.mongodb.Mongo;
import com.mongodb.MongoException;

/**
 *  Mongo  singleton holder
 * @author I038968
 *
 */
public class MongoConnection {
    
   static Logger logger = Logger.getLogger(MongoConnection.class);
    
    
    private static Mongo mongo;
    
    /**
     * Get Mongo Instance
     * @return
     * @throws DBConnectionFailureException
     */
    public static Mongo getMongo() throws DBConnectionFailureException{
	
	if( mongo == null ){
	    synchronized (MongoConnection.class) {
		if( mongo == null ){
		    try {
			mongo = new Mongo("localhost", 27017);
		    } catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			logger.error("error while connecting to Mongo", e);
			throw new DBConnectionFailureException();
		    } catch (MongoException e) {
			// TODO Auto-generated catch block
			logger.error("error while connecting to Mongo", e );
			throw new DBConnectionFailureException();
		    }
		}
	    }
	}
	
	
	
	return mongo;
    }

}
