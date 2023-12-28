package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAccountDao;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteRoleDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SqliteAccountDaoTest {

    @Test
    public void testGet() {
        Account account = new SqliteAccountDao().get("khanhtv");

        assertEquals("khanhtv", account.getUsername());
        assertEquals("1", account.getPassword());
        assertEquals(account.getRole(), new SqliteRoleDao().get(1));
    }
}
