package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.controller.admin.mail.AdminBlockMail;
import com.itep.hust.aimsgroup.controller.admin.mail.AdminCreateMail;
import com.itep.hust.aimsgroup.controller.admin.mail.AdminDeleteMail;
import com.itep.hust.aimsgroup.controller.admin.mail.AdminUpdateMail;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountAddValidator;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountUpdateValidator;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountValidator;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.AccountStatus;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.service.dao.AccountRoleDao;
import com.itep.hust.aimsgroup.service.dao.RoleDao;
import com.itep.hust.aimsgroup.service.email.EmailService;

import java.util.List;
public class AdminController {
    private final AccountDao accountDao;
    private final RoleDao roleDao;
    private final EmailService emailService;
    public AdminController(AccountDao accountDao, EmailService emailService, RoleDao roleDao) {
        this.accountDao = accountDao;
        this.emailService = emailService;
        this.roleDao = roleDao;
    }
    public List<Account> getAccounts() {
        return accountDao.getAll();
    }

    public boolean createAccount(String username, String password, List<Role> roles) {
        AccountValidator accountValidator = new AccountAddValidator(accountDao);

        Account account = new Account(username, password, roles, AccountStatus.ACTIVE);

        if (accountValidator.validateAccount(account)) {
            return false;
        }

        accountDao.insert(account);

        String content = AdminCreateMail.getContent(account);

        emailService.sendMail(account.getEmail(), "AIMS ADMIN", content);

        return true;
    }

    public boolean updateAccount(Account account) {
        AccountValidator accountValidator = new AccountUpdateValidator(accountDao);

        if (accountValidator.validateAccount(account)) {
            return false;
        }

        accountDao.update(account);

        emailService.sendMail(account.getEmail(), "AIMS Admin", AdminUpdateMail.getContent(account));

        return true;
    }

    public void deleteAccount(Account account) {
        accountDao.delete(account);
        emailService.sendMail(account.getEmail(), "AIMs Admin", AdminDeleteMail.getContent(account));
    }

    public void blockAccount(Account account) {
        account.setStatus(AccountStatus.BLOCK);
        accountDao.update(account);
        emailService.sendMail(account.getEmail(), "AIMs Admin", AdminBlockMail.getContent(account));
    }

    public void unblockAccount(Account account) {
        account.setStatus(AccountStatus.ACTIVE);
        accountDao.update(account);
        emailService.sendMail(account.getEmail(), "AIMs Admin", AdminBlockMail.getContent(account));
    }

    public List<Role> getAllRoles() {
        return roleDao.getAll();
    }
}
