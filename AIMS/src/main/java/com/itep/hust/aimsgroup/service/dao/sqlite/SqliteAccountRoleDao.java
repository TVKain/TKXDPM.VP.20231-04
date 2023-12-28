package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.account.AccountRole;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteAccountRoleDao implements Dao<AccountRole, Pair<String, String>> {
    @Override
    public List<AccountRole> getAll() {
        List<AccountRole> accountRoles = new ArrayList<>();

        String query = "SELECT * FROM AccountRole";

        Connection connection = SqliteDatabase.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AccountRole accountRole = new AccountRole();
                accountRole.setRoleName(resultSet.getString("roleName"));
                accountRole.setEmail(resultSet.getString("email"));
                accountRoles.add(accountRole);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accountRoles;
    }

    @Override
    public AccountRole get(Pair<String, String> id) {
        return null;
    }

    @Override
    public AccountRole insert(AccountRole accountRole) {
        return null;
    }

    @Override
    public AccountRole update(AccountRole accountRole) {
        return null;
    }

    @Override
    public AccountRole delete(AccountRole accountRole) {
        return null;
    }
}
