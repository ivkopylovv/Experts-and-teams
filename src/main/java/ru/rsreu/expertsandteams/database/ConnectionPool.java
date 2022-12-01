package ru.rsreu.expertsandteams.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class ConnectionPool {
    private static final String DATASOURCE_NAME = "java:/comp/env/jdbc/experts-and-teams";
    private static Connection connection;

    private ConnectionPool() {
    }

    public static Connection getConnection() {
        synchronized (ConnectionPool.class) {
            if (connection == null) {
                try {
                    Context initContext = new InitialContext();
                    DataSource dataSource = (DataSource) initContext.lookup(DATASOURCE_NAME);
                    connection = dataSource.getConnection();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return connection;
    }
}
