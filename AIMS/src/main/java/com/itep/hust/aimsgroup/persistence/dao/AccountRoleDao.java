package com.itep.hust.aimsgroup.persistence.dao;

import com.itep.hust.aimsgroup.model.account.AccountRole;
import javafx.util.Pair;

public interface AccountRoleDao extends Dao<AccountRole, Pair<Integer, String>> {
    void deleteByAccountId(Integer id);
}
