package com.apofig.multith.connectionpool.dao;

import com.apofig.multith.connectionpool.dom.Person;

import java.util.List;

public interface AddressDAO {
    void addPerson(Person person) throws DAOException;

    Person findPerson(String name) throws DAOException;

    List<Person> getAllPersons() throws DAOException;
}
