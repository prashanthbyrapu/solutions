package app.test;

import java.util.ArrayList;
import java.util.List;

import app.solutions.dao.factory.DAOFactory;
import app.solutions.exceptions.NoCollectionException;
import app.solutions.exceptions.ValidationFailedException;
import app.solutions.model.Address;
import app.solutions.model.BaseObject;
import app.solutions.model.Person;
import app.solutions.util.Utility;

public class TestModel {

    /**
     * @param args
     */
    public static void main(String[] args) throws NoCollectionException {

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
            BaseObject obj =  DAOFactory.getDAO(100,"Person").insert(person);

           // BaseObject obj = DAOFactory.getDAO(100,"Person").get("4feaed0e1bbbd2543ef79028");
            System.out.println(Utility.toJson(obj));
        } catch (ValidationFailedException e) {

            System.out.println(Utility.toJson(e.getErrors()));
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
