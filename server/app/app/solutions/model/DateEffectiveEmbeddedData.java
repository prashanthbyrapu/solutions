package app.solutions.model;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by niveditha on 4/5/14.
 */
public class DateEffectiveEmbeddedData {

    @NotNull
    private Date validFromDate;


    public Date getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = validFromDate;
    }
}
