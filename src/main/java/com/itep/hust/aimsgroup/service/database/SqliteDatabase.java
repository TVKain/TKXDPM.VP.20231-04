package com.itep.hust.aimsgroup.service.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for connecting to the database
 * @author tvkain
 */
public class SqliteDatabase {
    private static Connection connection;
    private static final String SQLITE_URL = String.format("jdbc:sqlite:%s",
            SqliteDatabase.class.getResource("/database/aims.sqlite"));
    private SqliteDatabase() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(SQLITE_URL);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
