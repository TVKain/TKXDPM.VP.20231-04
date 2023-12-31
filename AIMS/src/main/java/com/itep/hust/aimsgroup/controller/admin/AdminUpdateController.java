package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.controller.admin.mail.AdminUpdateMail;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountUpdateValidator;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountValidator;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.service.email.EmailService;

public class AdminUpdateController {
    private final AccountDao accountDao;

    private final EmailService emailService;
    private final AccountValidator accountValidator;
    public AdminUpdateController(AccountDao accountDao, EmailService emailService) {
        this.accountDao = accountDao;
        this.emailService = emailService;
        this.accountValidator = new AccountUpdateValidator(accountDao);
    }

    public boolean updateAccount(Account account) {
        if (accountValidator.validateAccount(account)) {
            return false;
        }

        accountDao.update(account);

        emailService.sendMail(account.getEmail(), "AIMS Admin", AdminUpdateMail.getContent(account));

        return true;
    }


}
