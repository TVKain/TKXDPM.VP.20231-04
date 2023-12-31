package com.itep.hust.aimsgroup.persistence.dao.sqlite;

import com.itep.hust.aimsgroup.model.account.AccountRole;
import com.itep.hust.aimsgroup.persistence.dao.AccountRoleDao;
import com.itep.hust.aimsgroup.persistence.database.SqliteDatabase;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteAccountRoleDao implements AccountRoleDao {
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
                accountRole.setAccountId(resultSet.getInt("accountId"));
                accountRoles.add(accountRole);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accountRoles;
    }

    @Override
    public AccountRole get(Pair<Integer, String> id) {
        return null;
    }

    @Override
    public AccountRole insert(AccountRole accountRole) {
        String query = "INSERT INTO AccountRole (accountId, roleName) VALUES (?, ?)";

        Connection connection = SqliteDatabase.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountRole.getAccountId());
            preparedStatement.setString(2, accountRole.getRoleName());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            if (rowsAffected > 0) {
                return accountRole;
            } else {
                throw new SQLException("Can not insert to AccountRole with values "
                        + accountRole.getAccountId() + " " + accountRole.getRoleName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public AccountRole update(AccountRole accountRole) {
        return null;
    }

    @Override
    public AccountRole delete(AccountRole accountRole) {
        String query = "DELETE FROM AccountRole WHERE accountId = ? AND roleName = ?";

        Connection connection = SqliteDatabase.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, accountRole.getAccountId());
            preparedStatement.setString(2, accountRole.getRoleName());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            if (rowsAffected > 0) {
                return accountRole;
            } else {
                throw new SQLException("Cannot delete AccountRole with values "
                        + accountRole.getAccountId() + " " + accountRole.getRoleName());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByAccountId(Integer id) {
        String query = "DELETE FROM AccountRole WHERE accountId = ?";

        Connection connection = SqliteDatabase.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            if (rowsAffected <= 0) {
                throw new SQLException("Cannot delete AccountRole with AccountId = " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
