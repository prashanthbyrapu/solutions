package app.solutions.model;

import app.solutions.validation.annotations.ExistsInListEntries;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

/**
 * Created by niveditha on 4/1/14.

**/

@Entity
public class Student extends  Person{

    // Student name
    @NotBlank(message = "Student name is required")
    @Length(min= 1, max= 60 )
    private String name;

    // Date Of Birth ( Required Field )
    //@NotBlank( message = "Student Date Of Birth is required")
    private Date dob;

    @NotBlank(message = "Father Name is required")
    private String fatherName;


    //@ExistsInListEntries( value = "Gender")
    private String gender;

    //@ExistsInListEntries( value = "Language")
    private String motherTongue;

   // @NotBlank( message = "Admission number can not be empty")
    private String admissionNumber;

    //  Class specific roll number
    private String rollNumber;

   // @ExistsInListEntries(value = "Religion")
    private String religion;

    //@ExistsInListEntries(value = "Reservation")
    private String reservation;













}
