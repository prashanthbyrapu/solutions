package app.solutions.model;

import app.solutions.validation.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niveditha on 5/1/14.
 */
public class SchoolClass extends MasterObject {



    @Reference(collectionName = "SchoolBranch", fieldName = "externalId")
    private String branchId;

    // Reference <p>ClassType.class</P>
    @Reference(collectionName = "ClassType", fieldName = "externalId")
    private String type;


    // No of Sections
    private Integer noOfSections = 0;

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Integer getNoOfSections() {
        return noOfSections;
    }

    public void setNoOfSections(Integer noOfSections) {
        this.noOfSections = noOfSections;
    }
}
