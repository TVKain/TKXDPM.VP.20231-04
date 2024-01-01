package com.itep.hust.aimsgroup.controller.account;

import com.itep.hust.aimsgroup.controller.account.mail.AdminBlockMail;
import com.itep.hust.aimsgroup.controller.account.mail.AdminCreateMail;
import com.itep.hust.aimsgroup.controller.account.mail.AdminDeleteMail;
import com.itep.hust.aimsgroup.controller.account.mail.AdminUpdateMail;
import com.itep.hust.aimsgroup.controller.account.validator.AccountCreateValidator;
import com.itep.hust.aimsgroup.controller.account.validator.AccountUpdateValidator;
import com.itep.hust.aimsgroup.exception.account.CreateAccountException;
import com.itep.hust.aimsgroup.exception.account.UpdateAccountException;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.AccountStatus;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import com.itep.hust.aimsgroup.persistence.dao.RoleDao;
import com.itep.hust.aimsgroup.subsystem.email.EmailSubsystem;

import java.util.List;
public class AccountController {
    private final AccountDao accountDao;
    private final RoleDao roleDao;
    private final EmailSubsystem emailSubsystem;
    public AccountController(AccountDao accountDao, EmailSubsystem emailSubsystem, RoleDao roleDao) {
        this.accountDao = accountDao;
        this.emailSubsystem = emailSubsystem;
        this.roleDao = roleDao;
    }
    public List<Account> getAccounts() {
        return accountDao.getAll();
    }

    public void createAccount(String email, String password, List<Role> roles)
            throws CreateAccountException {
        AccountCreateValidator accountCreateValidator = new AccountCreateValidator();

        accountCreateValidator.validateEmail(email, accountDao);
        accountCreateValidator.validatePassword(password);
        accountCreateValidator.validateRoles(roles);

        Account account = new Account(email, password, roles, AccountStatus.ACTIVE);

        accountDao.insert(account);

        String content = AdminCreateMail.getContent(account);

        emailSubsystem.sendMail(account.getEmail(), "AIMS ADMIN", content);

    }

    public void updateAccount(Integer id, String email, String password, List<Role> roles, AccountStatus accountStatus)
            throws UpdateAccountException {
        AccountUpdateValidator accountUpdateValidator = new AccountUpdateValidator();

        accountUpdateValidator.validateEmail(id, email, accountDao);
        accountUpdateValidator.validatePassword(password);
        accountUpdateValidator.validateRoles(roles);

        Account account = new Account(id, email, password, accountStatus, roles);

        accountDao.update(account);

        emailSubsystem.sendMail(account.getEmail(), "AIMS Admin", AdminUpdateMail.getContent(account));
    }

    public void deleteAccount(Account account) {
        accountDao.delete(account);
        emailSubsystem.sendMail(account.getEmail(), "AIMs Admin", AdminDeleteMail.getContent(account));
    }

    public void blockAccount(Account account) {
        account.setStatus(AccountStatus.BLOCK);
        accountDao.update(account);
        emailSubsystem.sendMail(account.getEmail(), "AIMs Admin", AdminBlockMail.getContent(account));
    }

    public void unblockAccount(Account account) {
        account.setStatus(AccountStatus.ACTIVE);
        accountDao.update(account);
        emailSubsystem.sendMail(account.getEmail(), "AIMs Admin", AdminBlockMail.getContent(account));
    }

    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }


}
