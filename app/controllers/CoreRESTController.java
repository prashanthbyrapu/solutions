package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;
import app.solutions.util.Utility;
import play.mvc.Controller;
import play.mvc.results.RenderJson;

public class CoreRESTController extends Controller {

    public static void getAll(String collectionName) {

	Map resultList = new HashMap();
	
	
	BaseDAO dao = DAOFactory.getDAO(collectionName);

	List<BaseObject> list = dao.getAll();
	
	resultList.put("RESULT", list);
	throw new RenderJson(Utility.toJson(resultList));

    }
    
    
    
    
    public static void getById(String collectionName, String id){
Map resultList = new HashMap();
	
	
	BaseDAO dao = DAOFactory.getDAO(collectionName);

	BaseObject obj = dao.get(id);
	
	System.out.println(id);
	
	resultList.put("RESULT", obj);
	throw new RenderJson(Utility.toJson(resultList));
    }
    
    public static void insert(String collectionName){
	
	BaseDAO dao = DAOFactory.getDAO(collectionName);
	String jsonString = request.params.get("jsonBody");
	
	try {
	   BaseObject object =  (BaseObject) Utility.fromJson(jsonString, Utility.getClassName(collectionName));
	   dao.insert(object);
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    
	    throw new RuntimeException(e);
	} catch (ValidationFailedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    throw new RuntimeException(e);
	}
	
	
    }
}
