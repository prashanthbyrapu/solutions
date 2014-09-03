package app.solutions.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by niveditha on 5/1/14.
 */
public class MasterObject extends BaseObject {

   // @NotBlank
    private String externalId;

    @NotBlank
    private String description;

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
