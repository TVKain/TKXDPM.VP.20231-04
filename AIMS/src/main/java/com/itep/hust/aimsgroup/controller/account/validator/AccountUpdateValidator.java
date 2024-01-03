package com.itep.hust.aimsgroup.controller.account.validator;

import com.itep.hust.aimsgroup.exception.account.UpdateAccountException;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public class AccountUpdateValidator {
    public void validateEmail(Integer id, String email, AccountDao accountDao) throws UpdateAccountException {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new UpdateAccountException("Invalid email");
        }

        Account accountDuplicate = accountDao.getAccountByEmail(email);

        if (accountDuplicate != null && !accountDuplicate.getId().equals(id)) {
            throw new UpdateAccountException("Invalid email - email already exists");
        }
    }

    public void validatePassword(String password) throws UpdateAccountException {
        if (password.length() < 8) {
            throw new UpdateAccountException("Invalid password - password must be at least 8 characters");
        }
    }

    public void validateRoles(List<Role> roles) throws UpdateAccountException {
        if (roles.isEmpty()) {
            throw new UpdateAccountException("Invalid roles - must have at least 1 role");
        }
    }
}
