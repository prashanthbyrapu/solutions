package app.test;

import java.util.ArrayList;
import java.util.List;

import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.Address;
import app.solutions.model.BaseObject;
import app.solutions.model.Person;

public class TestModel {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Person person = new Person();

        person.setName("Prashanth Kumar Byrapu");

        Address address = new Address();

        address.setAddressType("HOME");
        address.setHouseNumber("housenumber");
        address.setStreetName("stretname");

        List<Address> list = new ArrayList<Address>();

        list.add(address);

        person.setAddress(address);

        try {
          //  DAOFactory.getDAO("Person").insert(person);

            //BaseObject obj = DAOFactory.getDAO("Person").get("4feaed0e1bbbd2543ef79028");
            //System.out.println(obj.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
