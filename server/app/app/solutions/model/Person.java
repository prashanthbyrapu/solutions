package app.solutions.model;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import app.solutions.model.annotations.ChangeVersionEnabled;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;

@ChangeVersionEnabled
public class Person extends BaseObject {

    @NotBlank(message = "Person Name can not be empty")
    private String name;

    private Date dateOfBirth;

    private String placeOfBirth;

    private String religion;

    private String caste;

    private List<String> identificationMarks;

    private String photoId;


    @Embedded
    @Valid
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
