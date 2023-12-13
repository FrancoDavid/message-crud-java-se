package org.dark.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static String connectionUrl = "jdbc:mysql://localhost:8889/messages_app";
    private static String connectionUser = "root";
    private static String connectionPass = "root";
    private static Connection connection = null;
    private static BasicDataSource basicDataSource = null;

    public static BasicDataSource getInstancePool() throws SQLException {
        if (basicDataSource == null) {
            basicDataSource = new BasicDataSource();

            basicDataSource.setUrl(connectionUrl);
            basicDataSource.setUsername(connectionUser);
            basicDataSource.setPassword(connectionPass);

            basicDataSource.setInitialSize(3);
            basicDataSource.setMinIdle(3);
            basicDataSource.setMaxIdle(10);
            basicDataSource.setMaxTotal(10);
        }

        return basicDataSource;
    }

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(connectionUrl, connectionUser, connectionPass);
        }

        return connection;
    }

    public static Connection getConnectionPool() throws SQLException {
        return getInstancePool().getConnection();
    }
}
