package com.itep.hust.aimsgroup.dao.mysql;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.persistence.dao.mysql.MysqlAccountDao;
import org.junit.jupiter.api.Test;

public class MysqlAccountDaoTest {
    @Test
    void getTest() {
        MysqlAccountDao accountDao = new MysqlAccountDao();

        Account account = accountDao.get(1);

        System.out.println(account.getEmail());
    }
}
