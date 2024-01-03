package com.itep.hust.aimsgroup.persistence.dao.mysql;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.AccountRole;
import com.itep.hust.aimsgroup.model.account.AccountStatus;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import com.itep.hust.aimsgroup.persistence.database.MysqlDatabase;
import com.itep.hust.aimsgroup.persistence.database.MysqlDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlAccountDao implements AccountDao {

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();

        String query = "SELECT * FROM Account";

        try {
            PreparedStatement preparedStatement = MysqlDatabase.getConnection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Account account = new Account();

                account.setId(resultSet.getInt("id"));
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));
                account.setStatus(AccountStatus.valueOf(resultSet.getString("status")));

                List<AccountRole> accountRoles = new MysqlAccountRoleDao().getAll();

                List<Role> roles = accountRoles.stream()
                        .filter(accountRole -> accountRole.getAccountId().equals(account.getId()))
                        .map(accountRole -> new MysqlRoleDao().get(accountRole.getRoleName()))
                        .collect(Collectors.toList());

                account.setRoles(roles);

                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

    @Override
    public Account get(Integer id) {
        Account account = null;

        String accountQuery = "SELECT * FROM Account WHERE id = ?";

        try {
            PreparedStatement preparedStatement = MysqlDatabase.getConnection().prepareStatement(accountQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));
                account.setStatus(AccountStatus.valueOf(resultSet.getString("status")));


                List<AccountRole> accountRoles = new MysqlAccountRoleDao().getAll();

                Account finalAccount = account;

                List<Role> roles = accountRoles.stream()
                        .filter(accountRole -> accountRole.getAccountId().equals(finalAccount.getId()))
                        .map(accountRole -> new MysqlRoleDao().get(accountRole.getRoleName()))
                        .collect(Collectors.toList());

                account.setRoles(roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    @Override
    public Account getAccountByEmail(String email) {
        Account account = null;

        String accountQuery = "SELECT * FROM Account WHERE email = ?";

        try {
            PreparedStatement preparedStatement = MysqlDatabase.getConnection().prepareStatement(accountQuery);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));
                account.setStatus(AccountStatus.valueOf(resultSet.getString("status")));


                List<AccountRole> accountRoles = new MysqlAccountRoleDao().getAll();

                Account finalAccount = account;

                List<Role> roles = accountRoles.stream()
                        .filter(accountRole -> accountRole.getAccountId().equals(finalAccount.getId()))
                        .map(accountRole -> new MysqlRoleDao().get(accountRole.getRoleName()))
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
        String query = "INSERT INTO Account (email, password, status) VALUES (?, ?, ?)";

        try {
            PreparedStatement preparedStatement = MysqlDatabase.getConnection().prepareStatement(query);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getStatus().name());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting account failed, no rows affected.");
            }

            ResultSet resultSet = MysqlDatabase.getConnection().prepareStatement("SELECT LAST_INSERT_ID();").executeQuery();

            if (resultSet.next()) {
                account.setId(resultSet.getInt(1));
            }

            List<Role> roles = account.getRoles();
            if (roles != null && !roles.isEmpty()) {
                MysqlAccountRoleDao accountRoleDao = new MysqlAccountRoleDao();
                for (Role role : roles) {
                    AccountRole accountRole = new AccountRole();
                    accountRole.setRoleName(role.getRoleName());
                    accountRole.setAccountId(account.getId());
                    accountRoleDao.insert(accountRole);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }

    @Override
    public Account update(Account account) {

        String updateAccountQuery = "UPDATE Account SET email = ?, password = ?, status = ? WHERE id = ?";

        try {
            PreparedStatement updateAccountStatement = MysqlDatabase.getConnection().prepareStatement(updateAccountQuery);
            updateAccountStatement.setString(1, account.getEmail());
            updateAccountStatement.setString(2, account.getPassword());
            updateAccountStatement.setString(3, account.getStatus().name());
            updateAccountStatement.setInt(4, account.getId());

            int accountRowsAffected = updateAccountStatement.executeUpdate();

            updateAccountStatement.close();

            if (accountRowsAffected > 0) {
                List<Role> newRoles = account.getRoles();
                if (newRoles != null && !newRoles.isEmpty()) {
                    MysqlAccountRoleDao accountRoleDao = new MysqlAccountRoleDao();

                    // Delete existing roles for the account
                    accountRoleDao.deleteByAccountId(account.getId());

                    // Insert the new roles
                    for (Role role : newRoles) {
                        AccountRole accountRole = new AccountRole();
                        accountRole.setRoleName(role.getRoleName());
                        accountRole.setAccountId(account.getId());
                        accountRoleDao.insert(accountRole);
                    }
                }

                return account;
            } else {
                throw new SQLException("Updating account failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Account delete(Account account) {
        String query = "DELETE FROM Account Where id = ?";

        try {
            PreparedStatement preparedStatement = MysqlDatabase.getConnection().prepareStatement(query);

            preparedStatement.setInt(1, account.getId());

            int accountRowsAffected = preparedStatement.executeUpdate();

            preparedStatement.close();

            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
