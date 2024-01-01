package com.itep.hust.aimsgroup.controller.login;

import com.itep.hust.aimsgroup.controller.login.validator.AccountLoginValidator;
import com.itep.hust.aimsgroup.exception.account.LoginAccountException;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import com.itep.hust.aimsgroup.persistence.dao.RoleDao;

import java.util.List;

/*
 * SOLID ANALYSIS
 * SINGLE RESPONSIBILITY: This is only responsible for handling account login functionality
 * DEPENDENCY INVERSION: This class does not depend on concrete dao class, but depends on DAO interface
 */
public class AccountLoginController {
    public void login(String email, String password, Role role, AccountDao accountDao) throws LoginAccountException {
        AccountLoginValidator accountLoginValidator = new AccountLoginValidator();
        accountLoginValidator.validateLogin(email, password, role, accountDao);
    }

    public List<Role> getRoles(RoleDao roleDao) {
        return roleDao.getAll();
    }
}
