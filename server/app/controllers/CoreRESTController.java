package controllers;

import java.io.IOException;
import java.util.*;

import app.solutions.contexts.UserContext;
import app.solutions.core.bootstrap.Bootstrap;
import app.solutions.core.facade.Document;
import app.solutions.core.service.DocumentReadService;
import app.solutions.core.service.DocumentTextService;
import app.solutions.core.service.beans.DocumentTextBean;
import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;
import app.solutions.model.ValueLists;
import app.solutions.util.Utility;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.results.BadRequest;
import play.mvc.results.NotFound;
import play.mvc.results.RenderJson;

public class CoreRESTController extends BaseController {


    private static List<String> getRequestedFields() {
        String fields = Http.Request.current().params.get("fields");
        BaseObject document;
        List<String> fieldsToBeRequested = new ArrayList<String>();
        if (fields != null) {
            String[] tokens = fields.split(",");
            fieldsToBeRequested = Arrays.asList(tokens);
        }
        return fieldsToBeRequested;
    }

    /**
     * Get Document By ID
     *
     * @param collectionName
     */
    public static void getById(String collectionName, String id) {

        DocumentReadService readService = (DocumentReadService) Bootstrap.getInstance().getService(DocumentReadService.class);


        String response = null;
        try {
            BaseObject document;
            List<String> fields = getRequestedFields();
            if (fields != null && fields.size() > 0) {
                document = readService.byId(collectionName, id, fields);
            } else {
                document = readService.byId(collectionName, id);
            }

            if (document == null) {
                throw new NotFound("No Document exists with id: " + id);
            }
            response = Utility.toJson(document);
        } catch (NoCollectionException e) {
            throw new NotFound("Collection Not Found : " + collectionName);
        } catch (NoDocumentExists noDocumentExists) {
            throw new NotFound("No Document exists with id : " + id);
        }
        throw new RenderJson(response);
    }


    /**
     * Get All Documents.
     *
     * @param collectionName
     */
    public static void getAll(String collectionName) {
        DocumentReadService readService = (DocumentReadService) Bootstrap.getInstance().getService(DocumentReadService.class);
        try {
            List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
            List<String> fields = getRequestedFields();
            if (fields != null && fields.size() > 0) {
                response = readService.getAll(collectionName, fields, true);
                throw new RenderJson(Utility.toJson(response));
            } else {
                List<BaseObject> documents = readService.getAll(collectionName);
                throw new RenderJson(Utility.toJson(documents));
            }


        } catch (NoCollectionException e)

        {
            throw new NotFound("Collection Not found: " + collectionName);
        }

    }


    public static void insert(String collectionName) {
        String requestBody = Http.Request.current().params.get("body");
        Response response = new Response();
        if (Strings.isNullOrEmpty(requestBody)) {
            response.setSuccess(false);
            response.setErrorMessages(Lists.newArrayList("No Data Receieved"));
        } else {
            try {
                Class modelClass = Utility.getClassName(collectionName);
                // Call the API
                BaseObject document = Document.insert((BaseObject) Utility.fromJson(requestBody, modelClass), getUserContext());
                // Populate return data
                response.setSuccess(true);
                response.setObjectId(document.getId().toString());
                response.setSuccessMessages(Lists.newArrayList("Document Created successfully"));
            } catch (ClassNotFoundException e) {
                throw new NotFound("Collection Not found : " + collectionName);
            } catch (ValidationFailedException e) {
                response.setSuccess(false);
                response.setErrorMessages(e.getErrors());
            } catch (NoCollectionException e) {
                throw new NotFound("Collection Not found : " + collectionName);
            }
        }


        // now render the json
        throw new RenderJson(Utility.toJson(response));
    }

    /**
     * Get the User Context from request.
     *
     * @return
     */
    private static UserContext getUserContext() {
        UserContext userContext = new UserContext();
        String client = Http.Request.current().params.get("client");
        if (client != null) {
            userContext.client = Integer.parseInt(client);
        }

        if (client == null) {
            try {
                response.out.write("Client is not passed".getBytes());
            } catch (IOException e) {
                //it is ok.
            }
            throw new BadRequest();
        }

        String user = Http.Request.current().params.get("user");
        if (user != null) {
            userContext.userId = user;
        }
        return userContext;
    }


    public static void getTexts(String collectionName) {
        DocumentTextService readService = (DocumentTextService) Bootstrap.getInstance().getService(DocumentTextService.class);

        List<DocumentTextBean> response = null;
        try {
            response = readService.getTexts(collectionName);
        } catch (NoCollectionException e) {
            throw new NotFound("Collection Not Found : " + collectionName);
        }
        throw new RenderJson(response);
    }


    public static void getListEntries(String listName) {
        DocumentReadService readService = (DocumentReadService) Bootstrap.getInstance().getService(DocumentReadService.class);


        String response = null;
        try {
            List<BaseObject> documents;
            documents = readService.getByFieldValue(ValueLists.class.getSimpleName(), "listId", listName);
            if (documents == null || documents.size() == 0) {
                throw new NotFound("NO Lists exists with list id " + listName);
            }
            response = Utility.toJson(((ValueLists) documents.get(0)).getListEntries());
        } catch (NoCollectionException e) {
            throw new NotFound("Collection Not Found : " + "ValueLists");
        }

        throw new RenderJson(response);
    }

}
