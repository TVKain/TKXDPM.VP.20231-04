package com.itep.hust.aimsgroup.controller.login.validator;

import com.itep.hust.aimsgroup.exception.account.LoginAccountException;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.AccountStatus;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;

public class AccountLoginValidator {

    public void validateLogin(String email, String password, Role role, AccountDao accountDao) throws LoginAccountException {

        Account accountDb = accountDao.getAccountByEmail(email);

        if (accountDb == null) {
            throw new LoginAccountException("Login fail - account or password is wrong");
        }

        if (!password.equals(accountDb.getPassword())) {
            throw new LoginAccountException("Login fail - account or password is wrong");
        }

        if (!accountDb.getRoles().contains(role)) {
            throw new LoginAccountException("Login fail - account does not have selected role");
        }

        if (accountDb.getStatus().equals(AccountStatus.BLOCK)) {
            throw new LoginAccountException("Login fail - your account has been blocked");
        }
    }
}
