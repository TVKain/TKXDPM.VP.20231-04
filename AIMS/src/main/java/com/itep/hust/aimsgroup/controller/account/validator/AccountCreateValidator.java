package com.itep.hust.aimsgroup.controller.account.validator;

import com.itep.hust.aimsgroup.exception.account.CreateAccountException;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class AccountCreateValidator {
    public void validateEmail(String email, AccountDao accountDao) throws CreateAccountException {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new CreateAccountException("Invalid email");
        }

        Account accountDb = accountDao.getAccountByEmail(email);

        if (accountDb != null) {
            throw new CreateAccountException("Invalid email - email already exists");
        }
    }


    public void validatePassword(String password) throws CreateAccountException {
        if (password.length() < 8) {
            throw new CreateAccountException("Invalid password - password must be at least 8 characters");
        }
    }

    public void validateRoles(List<Role> roles) throws CreateAccountException {
        if (roles.isEmpty()) {
            throw new CreateAccountException("Invalid roles - must have at least 1 role");
        }
    }
}
