package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.AccountRole;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class SqliteAccountDao implements Dao<Account, String> {

    @Override
    public List<Account> getAll() {
        return null;
    }

    @Override
    public Account get(String email) {
        Account account = null;

        String accountQuery = "SELECT * FROM Account WHERE email = ?";


        try {
            PreparedStatement preparedStatement = SqliteDatabase.getConnection().prepareStatement(accountQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));

                List<AccountRole> accountRoles = new SqliteAccountRoleDao().getAll();

                Account finalAccount = account;

                List<Role> roles = accountRoles.stream()
                        .filter(accountRole -> accountRole.getEmail().equals(finalAccount.getEmail()))
                        .map(accountRole -> new SqliteRoleDao().get(accountRole.getRoleName()))
                        .collect(Collectors.toList());

                account.setRoles(roles);
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
