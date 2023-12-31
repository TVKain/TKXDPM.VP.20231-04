package com.itep.hust.aimsgroup.controller.admin;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.AccountStatus;
import com.itep.hust.aimsgroup.service.dao.AccountDao;

public class AdminStatusController {
    private final AccountDao accountDao;
    public AdminStatusController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


}
