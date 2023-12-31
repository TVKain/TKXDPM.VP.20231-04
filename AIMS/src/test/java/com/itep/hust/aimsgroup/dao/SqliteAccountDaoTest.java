package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAccountDao;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteRoleDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SqliteAccountDaoTest {

    @Test
    public void testGet() {
        Account account = new SqliteAccountDao().get(1);

        assertEquals("tvkain.it@gmail.com", account.getEmail());
        assertEquals("1", account.getPassword());
        assertEquals(account.getRoles().size(), 2);
    }
}
