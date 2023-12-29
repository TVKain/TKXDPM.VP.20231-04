package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.controller.admin.validator.AccountAddValidator;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountValidator;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.util.Popup;

public class AdminAddController {
    AccountDao accountDao;

    AccountValidator accountValidator;
    public AdminAddController(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.accountValidator = new AccountAddValidator(accountDao);
    }
    public boolean createAccount(Account account) {
        if (accountValidator.validateAccount(account)) {
            return false;
        }

        accountDao.insert(account);
        Popup.showSuccess("Account created successfully");

        return true;
    }


}
