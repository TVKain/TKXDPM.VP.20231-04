package com.itep.hust.aimsgroup.service.dao;

import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDao implements Dao<Admin> {
    @Override
    public List<Admin> getAll() {
        return null;
    }

    @Override
    public Admin get(Integer id) {
        return null;
    }

    public Admin getByUsername(String username) {
        String query = "SELECT * FROM Admin WHERE username=?";

        Connection connection = SqliteDatabase.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setUsername(resultSet.getString("username"));
                admin.setPassword(resultSet.getString("password"));
                return admin;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Admin insert(Admin admin) {
        return null;
    }

    @Override
    public Admin update(Admin admin) {
        return null;
    }

    @Override
    public Admin delete(Admin admin) {
        return null;
    }
}
