package com.itep.hust.aimsgroup.service.database;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Phân tích SOLID
 * Vi phạm tính D trong SOLID ( Dependency inversion principle) bởi các loại cơ sở dữ liệu như MySQL, SQLite, .. đều cần đến phương thức getConnection()
 * closeConnection() vì vậy ta nên tạo 1 interface để impliment lại bởi 1 ngày nào đó ta muốn đổi loại cơ sở dữ liệu thì chỉ cần tạo 1 lớp mới và
 * impliment nó
 */

/**
 * This class is responsible for connecting to the database
 * This class uses the singleton pattern to ensure that there's only one single connection object to the database
 * @author tvkain
 */
public class SqliteDatabase {
    private static Connection connection;
    private static final String FILE_URL = "aims.db";
    private static final String SQLITE_URI = String.format("jdbc:sqlite:%s", FILE_URL);
    private SqliteDatabase() {
    }
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(SQLITE_URI);
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

    /**
     * Seed the database only use for development purposes
     */
    public static void seed() {

        Path path = Path.of(FILE_URL);

        try {
            if (Files.exists(path)) {
                Files.delete(path);
            }

            Files.createFile(path);
            System.out.println(FILE_URL + " created successfully");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }


        String sqlFilePath = SqliteDatabase.class.getResource("/database/query.sql").getPath();

        try {
            Connection connection = SqliteDatabase.getConnection();
            Statement statement = connection.createStatement();

            StringBuilder sqlContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sqlContent.append(line).append("\n");
                }
            }

            String[] sqlStatements = sqlContent.toString().split(";");

            for (String sql : sqlStatements) {
                if (!sql.trim().isEmpty()) {
                    statement.addBatch(sql);
                }
            }

            statement.executeBatch();

            System.out.println("SQL script executed successfully.");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
