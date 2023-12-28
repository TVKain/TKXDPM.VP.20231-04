package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqliteAccountDao implements Dao<Account, String> {

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account get(String username) {
        Account account = null;

        String accountQuery = "SELECT * FROM Account WHERE username = ?";
        String roleQuery = "SELECT * FROM Role WHERE id = ?";

        try {
            PreparedStatement preparedStatement = SqliteDatabase.getConnection().prepareStatement(accountQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));

                Role role = new SqliteRoleDao().get(resultSet.getInt("roleId"));
                account.setRole(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account insert(Account account) {
        return null;
    }

    @Override
    public Account update(Account account) {
        return null;
    }

    @Override
    public Account delete(Account account) {
        return null;
    }
}
