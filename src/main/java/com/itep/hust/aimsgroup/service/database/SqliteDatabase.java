package com.itep.hust.aimsgroup.service.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for connecting to the database
 * @author tvkain
 */

/**
 * Phân tích SOLID
 * Vi phạm tính D trong SOLID ( Dependency inversion principle) bởi các loại cơ sở dữ liệu như MySQL, SQLite, .. đều cần đến phương thức getConnection()
 * closeConnection() vì vậy ta nên tạo 1 interface để impliment lại bởi 1 ngày nào đó ta muốn đổi loại cơ sở dữ liệu thì chỉ cần tạo 1 lớp mới và
 * impliment nó
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
