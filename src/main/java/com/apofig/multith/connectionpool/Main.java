package com.apofig.multith.connectionpool;

import com.apofig.multith.connectionpool.dao.AddressDAOImpl;
import com.apofig.multith.connectionpool.utils.PropertiesReader;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = PropertiesReader.read("database.properties");
        AddressDAOImpl dao = new AddressDAOImpl(properties);

        AddressBook book = new AddressBook(dao);

        System.out.println(book.getSize());
    }
}
