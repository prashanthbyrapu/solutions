package app.solutions.model;

import app.solutions.validation.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niveditha on 5/1/14.
 */
public class SchoolClass extends MasterObject {

    @Reference(collectionName = "SchoolBranch", fieldName = "externalId")
    private String branchExternalId;

    @Reference(collectionName = "ClassType", fieldName = "externalId")
    private String type;

    private List<String> classSections = new ArrayList<String>();
}
