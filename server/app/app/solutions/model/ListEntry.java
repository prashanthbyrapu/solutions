package app.solutions.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;


@Embedded
public class ListEntry extends BaseObject {

    @NotEmpty
    @Length(min = 1, max = 15)
    private String entryId;


    @NotEmpty
    @Length(min = 1, max = 40)
    private String entryDescription;


    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
    }

    public String getEntryDescription() {
        return entryDescription;
    }

    public void setEntryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
    }


}
