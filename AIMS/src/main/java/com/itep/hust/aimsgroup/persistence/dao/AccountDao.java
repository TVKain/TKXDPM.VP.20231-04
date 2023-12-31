package com.itep.hust.aimsgroup.persistence.dao;

import com.itep.hust.aimsgroup.model.account.Account;

public interface AccountDao extends Dao<Account, Integer> {
   Account getAccountByEmail(String email);
}
