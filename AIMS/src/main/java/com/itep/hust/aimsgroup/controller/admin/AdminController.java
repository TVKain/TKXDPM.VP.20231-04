package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.service.dao.Dao;

import java.util.List;
public class AdminController {
    private final AccountDao accountDao;
    public AdminController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> getAccounts() {
        return accountDao.getAll();
    }

    public void deleteAccount(Account account) {
        accountDao.delete(account);
    }


}
