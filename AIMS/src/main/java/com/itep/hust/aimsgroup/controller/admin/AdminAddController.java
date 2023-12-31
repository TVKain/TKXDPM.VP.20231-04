package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.controller.admin.mail.AdminCreateMail;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountAddValidator;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountValidator;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.service.email.EmailService;

public class AdminAddController {
    AccountDao accountDao;

    EmailService emailService;
    AccountValidator accountValidator;
    public AdminAddController(AccountDao accountDao, EmailService emailService) {
        this.accountDao = accountDao;
        this.emailService = emailService;
        this.accountValidator = new AccountAddValidator(accountDao);
    }
    public boolean createAccount(Account account) {
        if (accountValidator.validateAccount(account)) {
            return false;
        }

        accountDao.insert(account);

        String content = AdminCreateMail.getContent(account);

        emailService.sendMail(account.getEmail(), "AIMS ADMIN", content);

        return true;
    }


}
