package app.solutions.model;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Reference;

@Embedded
public class Communication extends BaseObject{

    
    @Reference
    private CommunicationType type;
    
    // ID on which we can reach..might be email, phone , skype id or anything.
    private String communicationId;
}
