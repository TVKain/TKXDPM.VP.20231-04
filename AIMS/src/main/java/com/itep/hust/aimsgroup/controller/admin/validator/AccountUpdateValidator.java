package com.itep.hust.aimsgroup.controller.admin.validator;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.AccountDao;

public class AccountUpdateValidator extends AccountValidator {
    public AccountUpdateValidator(AccountDao accountDao) {
        super(accountDao);
    }

    @Override
    public boolean validateAccount(Account account) {
        if (!validateEmail(account.getEmail())) {
            return true;
        }

        if (!validatePassword(account.getPassword())) {
            return true;
        }

        if (!validateRoles(account.getRoles())) {
            return true;
        }

        return false;
    }
}
