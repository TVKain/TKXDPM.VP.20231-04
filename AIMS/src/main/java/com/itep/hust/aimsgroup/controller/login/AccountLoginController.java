package com.itep.hust.aimsgroup.controller.login;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.service.dao.Dao;

import java.util.List;

/*
 * SOLID ANALYSIS
 * SINGLE RESPONSIBILITY: This is only responsible for handling account login functionality
 * DEPENDENCY INVERSION: This class does not depend on concrete dao class, but depends on DAO interface
 */
public class AccountLoginController {


    public boolean authenticateLogin(Account account, Role role, AccountDao accountDao) {
        Account accountDb = accountDao.getAccountByEmail(account.getEmail());

        if (accountDb == null) {
            return false;
        }

        if (!account.getPassword().equals(accountDb.getPassword())) {
            return false;
        }

        return accountDb.getRoles().contains(role);
    }

    public List<Role> getRoles(Dao<Role, String> roleDao) {
        return roleDao.getAll();
    }
}
