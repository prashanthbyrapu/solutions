package app.solutions.model;

import app.solutions.validation.annotations.ExistsInListEntries;
import app.solutions.validation.annotations.Reference;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.NotNull;

/**
 * Created by niveditha on 5/1/14.
 */
@Embedded
public class FeeConcession {

    @Reference(collectionName = "FeeConcessionType", fieldName = "extneralId")
    private String type;

    @ExistsInListEntries(value = "FeeConcessionMode")
    private String concessionMode;

    @Reference(collectionName = "FeeType", fieldName = "externalId")
    private String feeType;

    @NotNull
    private Double amount;
}
