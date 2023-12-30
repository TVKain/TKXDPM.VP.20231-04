package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.controller.admin.validator.AccountUpdateValidator;
import com.itep.hust.aimsgroup.controller.admin.validator.AccountValidator;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.AccountDao;
import com.itep.hust.aimsgroup.util.Popup;

public class AdminUpdateController {
    private final AccountDao accountDao;
    private final AccountValidator accountValidator;
    public AdminUpdateController(AccountDao accountDao) {
        this.accountDao = accountDao;
        this.accountValidator = new AccountUpdateValidator(accountDao);
    }

    public boolean updateAccount(Account account) {
        if (accountValidator.validateAccount(account)) {
            return false;
        }
        accountDao.update(account);

        Popup.showSuccess("Update media success");
        return true;
    }


}
