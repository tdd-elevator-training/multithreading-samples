package com.apofig.multith.connectionpool.dao.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorkerThread extends Thread {
    private static Logger logger = Logger.getLogger(WorkerThread.class.getName());

    private Connection connection;
    private Runnable task;

    public WorkerThread(Connection connection, Runnable task) {
        this.task = task;
        this.connection = connection;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "WorkerThread start task!");
        try {
            task.run();
        } finally {
            logger.log(Level.INFO, "WorkerThread finished task. Connection " + connection.hashCode() + " closed!");
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
