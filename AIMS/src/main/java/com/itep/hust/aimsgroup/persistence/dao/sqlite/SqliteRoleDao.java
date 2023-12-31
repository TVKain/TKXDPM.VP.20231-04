package com.itep.hust.aimsgroup.persistence.dao.sqlite;

import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.RoleDao;
import com.itep.hust.aimsgroup.persistence.database.SqliteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteRoleDao implements RoleDao {

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM Role";

        try (Connection connection = SqliteDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Role role = new Role();
                role.setRoleName(resultSet.getString("roleName"));
                roles.add(role);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roles;
    }

    @Override
    public Role get(String roleName) {
        Role role = null;
        String query = "SELECT * FROM Role WHERE roleName = ?";

        try {
            Connection connection = SqliteDatabase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roleName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                role = new Role();
                role.setRoleName(resultSet.getString("roleName"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }

    @Override
    public Role insert(Role role) {
        return null;
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Role delete(Role role) {
        return null;
    }
}
