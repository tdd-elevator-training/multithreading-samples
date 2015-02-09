package com.apofig.multith.connectionpool.dao;

import com.apofig.multith.connectionpool.dao.pool.ConnectionPool;
import com.apofig.multith.connectionpool.dao.pool.ConnectionRunner;
import com.apofig.multith.connectionpool.dom.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDAOImpl implements AddressDAO {
    private static Logger logger = Logger.getLogger(AddressDAOImpl.class.getName());

    ConnectionPool connections;

    public AddressDAOImpl(Properties properties) {
        try {
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.WARNING, "Cant find jdbc driver", e);
        }
        connections = ConnectionPool.with(properties).andThreads(18);
    }

    @Override
    public void addPerson(final Person person) throws DAOException {
        connections.query("add person",
                "insert into AddressEntry values (?, ?, ?)",
                new ConnectionRunner() {
                    @Override
                    public Object connect(PreparedStatement statement) throws SQLException {
                        statement.setLong(1, System.currentTimeMillis());
                        statement.setString(2, person.getName());
                        statement.setString(3, person.getPhoneNumber());
                        statement.executeUpdate();
                        return null;
                    }
                });
    }

    @Override
    public Person findPerson(final String name) throws DAOException {
        return connections.query("find person",
                "select * from AddressEntry where name = '" + name + "'",
                new ConnectionRunner<Person>() {
                    @Override
                    public Person connect(PreparedStatement statement) throws SQLException {
                        try (ResultSet data = statement.executeQuery()) {
                            if (data.next()) {
                                String foundName = data.getString("name");
                                String phoneNumber = data.getString("phoneNumber");
                                long date = data.getLong("timestamp");

                                return new Person(foundName, phoneNumber, date);
                            }
                            return null;
                        }
                    }
                });
    }

    @Override
    public List<Person> getAllPersons() throws DAOException {
        return connections.query("get all persons",
                "select * from AddressEntry",
                new ConnectionRunner<List<Person>>() {
                    @Override
                    public List<Person> connect(PreparedStatement statement) throws SQLException {
                        try (ResultSet data = statement.executeQuery()) {
                            List<Person> result = new LinkedList<Person>();
                            while (data.next()) {
                                String name = data.getString("name");
                                String number = data.getString("phoneNumber");
                                long date = data.getLong("timestamp");

                                result.add(new Person(name, number, date));
                            }
                            return result;
                        }
                    }
                });
    }
}
