package com.itep.hust.aimsgroup.controller;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.Dao;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * SOLID ANALYSIS
 * SINGLE RESPONSIBILITY: This is only responsible for handling account login functionality
 * DEPENDENCY INVERSION: This class does not depend on concrete dao class, but depends on DAO interface
 */
public class AccountLoginController {


    public boolean authenticateLogin(Account account, Dao<Account, String> accountDao) {
        Account accountDb = accountDao.get(account.getUsername());
        return account.equals(accountDb);
    }

    public List<Role> getRoles(Dao<Role, Integer> roleDao) {
        return roleDao.getAll();
    }
}
