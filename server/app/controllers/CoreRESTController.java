package controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.solutions.contexts.UserContext;
import app.solutions.core.bootstrap.Bootstrap;
import app.solutions.core.facade.Document;
import app.solutions.core.service.DocumentReadService;
import app.solutions.dao.BaseDAO;
import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.NoDocumentExists;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.BaseObject;
import app.solutions.util.Utility;
import com.google.common.collect.Lists;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.results.BadRequest;
import play.mvc.results.NotFound;
import play.mvc.results.RenderJson;

public class CoreRESTController extends BaseController {


    /**
     * Get Document By ID
     *
     * @param collectionName

     */
    public static void getById(String collectionName, String id) {

        DocumentReadService readService = (DocumentReadService) Bootstrap.getInstance().getService(DocumentReadService.class);


        String response = null;
        try {
            String fields = Http.Request.current().params.get("fields");
            BaseObject document;
            if (fields != null) {
                String[] tokens = fields.split(",");
                List<String> fieldsToBeRequested = Arrays.asList(tokens);
                document = readService.byId(collectionName, id, fieldsToBeRequested);
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

        try {
            List<BaseObject> documents = Document.getAllDocuments(collectionName, getUserContext());

            throw new RenderJson(Utility.toJson(documents));
        } catch (NoCollectionException e)

        {
            throw new NotFound("Collection Not found: " + collectionName);
        }

    }


    public static void insert(String collectionName) {
        String requestBody = Http.Request.current().params.get("body");
        Response response = new Response();
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

        // now render the json
        throw new RenderJson(Utility.toJson(response));
    }

    /**
     *  Get the User Context from request.
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

}
