import app.solutions.contexts.UserContext;
import com.google.inject.Provider;


/**
 * Created by niveditha on 19/4/14.
 */
public class TestUserContextProvider implements Provider<UserContext> {

    @Override
    public UserContext get() {

        UserContext userContext = new UserContext();
        userContext.client = 101;
        userContext.userId = "abcd";


        return userContext;
    }
}
