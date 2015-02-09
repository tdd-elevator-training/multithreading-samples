package com.apofig.multith.connectionpool;

import com.apofig.multith.connectionpool.dao.AddressDAO;
import com.apofig.multith.connectionpool.dao.DAOException;
import com.apofig.multith.connectionpool.dom.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressBook {

    private final AddressDAO dao;

    public AddressBook(AddressDAO dao) {
        this.dao = dao;
    }
    public int getSize() {
        List<Person> people = getAllPersons();
        return people.size();
    }

    public List<Person> getAllPersons() {
        try {
            return dao.getAllPersons();
        } catch (DAOException e) {
            return Arrays.asList();
        }
    }

    public String getMobile(String personName) {
        Person person = getPerson(personName);
        return person.getPhoneNumber();
    }

    private Person getPerson(String personName) {
        try {
            Person person = dao.findPerson(personName);
            if (person == null) {
                return Person.NULL;
            }
            return person;
        } catch (DAOException e) {
            return Person.NULL;
        }
    }

    public List<String> getPersonNamesTruncated(int byLength) {
        List<Person> people = getAllPersons();

        List<String> result = new ArrayList<String>(people.size());
        for (Person person : people) {
            result.add(person.getName(byLength));
        }
        return result;
    }

}
