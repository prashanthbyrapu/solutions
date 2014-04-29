package app.solutions.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by niveditha on 5/1/14.
 */
public class SchoolBranch extends MasterObject {

    @NotBlank
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
