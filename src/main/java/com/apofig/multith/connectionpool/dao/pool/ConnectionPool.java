package com.apofig.multith.connectionpool.dao.pool;

import com.apofig.multith.connectionpool.dao.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPool {
    private static Logger logger = Logger.getLogger(ConnectionPool.class.getName());

    private ExecutorService executor;
    private Properties properties;

    public ConnectionPool(final Properties properties) {
        this.properties = properties;
    }

    public static ConnectionPool with(Properties properties) {
        return new ConnectionPool(properties);
    }

    public ConnectionPool andThreads(int count) {
        this.executor = Executors.newFixedThreadPool(count, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new WorkerThread(getConnection(), runnable);
            }
        });
        return this;
    }

    public <T> T query(final String message, final String query,
                       final ConnectionRunner<T> runner) throws DAOException {
        if (executor == null) {
            andThreads(1);
        }

        Future<T> result = executor.submit(new Callable<T>() {
            @Override
            public T call() throws Exception {
                try {
                    WorkerThread thread = (WorkerThread) Thread.currentThread();
                    Connection connection = thread.getConnection();
                    logger.log(Level.INFO, "Query on connection " + connection.hashCode());

                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        return (T) runner.connect(statement);
                    }
                } catch (SQLException e) {
                    throw error(e, message);
                }
            }
        });
        try {
            return result.get();
        } catch (InterruptedException e) {
            throw error(e, message);
        } catch (ExecutionException e) {
            throw error(e, message);
        }
    }

    private DAOException error(Exception exception, String message) throws DAOException {
        String log = "Can not " + message;
        logger.log(Level.WARNING, log, exception);
        return new DAOException(log, exception);
    }

    public Connection getConnection() throws DAOException {
        logger.log(Level.INFO, "Get connection!");

        try {
            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (SQLException e) {
            throw error(e, "get connection");
        }
    }

    public void shutdown() {
        logger.log(Level.INFO, "Shutdown pool!");

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw error(e, "shutdown connection pool");
        }
    }
}