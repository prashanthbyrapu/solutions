package app.solutions.model;

import app.solutions.validation.annotations.Reference;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by niveditha on 5/1/14.
 */

@Embedded
public class ClassFee {

    @Reference(collectionName= "FeeType", fieldName="externalId")
    private String feeType;

    @NotNull(message = "Valid from date is required.")
    private Date validFromDate;

    @NotNull( message =" Amount is required")
    private Double amount;


}
