package com.apofig.multith.connectionpool.dao;

import com.apofig.multith.connectionpool.AddressBook;
import com.apofig.multith.connectionpool.dao.pool.ConnectionPool;
import com.apofig.multith.connectionpool.dao.pool.ConnectionRunner;
import com.apofig.multith.connectionpool.dom.Person;
import com.apofig.multith.connectionpool.utils.PropertiesReader;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class AddressBookTest {

    private Properties properties;
    private AddressDAOImpl dao;
    private AddressBook book;

    @Before
    public void setUp() throws Exception {
        properties = PropertiesReader.read("test-database.properties");
        dao = new AddressDAOImpl(properties);

        book = new AddressBook(dao);

        cleanTable(properties);
    }

    @Test
    public void test() throws DAOException {
        // given
        dao.addPerson(new Person("alex", "0993527", 0));
        dao.addPerson(new Person("balex", "0993467", 0));
        dao.addPerson(new Person("malex", "34534467", 0));

        // when then
        int times = 10;
        runTimesInThread(times, new Runnable() {
            @Override
            public void run() {
                assertEquals(3, book.getSize());
                assertEquals("0993527", book.getMobile("alex"));
            }
        });

        runTimesInThread(times, new Runnable() {
            @Override
            public void run() {
                assertEquals("[al, ba, ma]", book.getPersonNamesTruncated(2).toString());
            }
        });

        runTimesInThread(times, new Runnable() {
            @Override
            public void run() {
                assertEquals("[Person{name='alex', phoneNumber=0993527}, " +
                                "Person{name='balex', phoneNumber=0993467}, " +
                                "Person{name='malex', phoneNumber=34534467}]",
                        book.getAllPersons().toString());
            }
        });

        dao.connections.shutdown();
    }

    private void runTimesInThread(final int count, final Runnable runnable) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < count; index++) {
                    if (runnable != null) {
                        runnable.run();
                    }
                    sleep(100);
                }
            }
        }).run();
    }

    private void sleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cleanTable(Properties properties) throws DAOException {
        ConnectionPool.with(properties).query("delete all",
                "delete from AddressEntry", new ConnectionRunner<Object>() {
                    @Override
                    public Object connect(PreparedStatement statement) throws SQLException {
                        statement.execute();
                        return null;
                    }
                });
    }
}
