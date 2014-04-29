package app.solutions.contexts.provider;

import app.solutions.contexts.UserContext;
import com.google.inject.Provider;
import play.mvc.Http;
import play.mvc.results.BadRequest;



/**
 *
 * get the user context from Play Request.
 * Created by niveditha on 12/4/14.
 */
public class PlayUserContextProvider implements Provider<UserContext> {
    @Override
    public UserContext get() {

        UserContext userContext = new UserContext();

        // Get Client from http Request
        String client = Http.Request.current().params.get("client");
        if (client != null) {
            userContext.client = Integer.parseInt(client);
        }

        if (client == null) {
            throw new BadRequest();
        }

        // Get user from http
        String user = Http.Request.current().params.get("user");
        if (user != null) {
            userContext.userId = user;
        }
        return userContext;


    }
}
