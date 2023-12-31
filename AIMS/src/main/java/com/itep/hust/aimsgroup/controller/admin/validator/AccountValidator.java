package com.itep.hust.aimsgroup.controller.admin.validator;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import com.itep.hust.aimsgroup.util.Popup;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.List;

public abstract class AccountValidator {
    private final AccountDao accountDao;
    public AccountValidator(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public abstract boolean validateAccount(Account account);

    protected boolean validateRoles(List<Role> roles) {
        if (roles.isEmpty()) {
            Popup.showError("Must select at least 1 role");
            return false;
        }

        return true;
    }

    protected boolean validatePassword(String password) {
        if (password.isBlank()) {
            Popup.showError("Password is invalid");
            return false;
        }

        return true;
    }
    protected boolean validateEmailDuplicate(Account account) {
        Account checkAccount = accountDao.getAccountByEmail(account.getEmail());

        if (checkAccount != null) {
            if (checkAccount.getId().equals(account.getId())) {
                return true;
            }

            Popup.showError("There is already an account with the specified email");
            return false;
        }

        return true;
    }
    protected boolean validateEmail(String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            Popup.showError("Email is invalid");
            return false;
        }

        return true;
    }
}
