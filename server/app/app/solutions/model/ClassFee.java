package app.solutions.model;

import app.solutions.validation.annotations.Reference;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by niveditha on 5/1/14.
 */

@Embedded
public class ClassFee extends  DateEffectiveEmbeddedData{


    @Reference(collectionName= "FeeType", fieldName="externalId")
    private String feeType;

    @NotNull( message =" Amount is required")
    private Double amount;


    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


}
