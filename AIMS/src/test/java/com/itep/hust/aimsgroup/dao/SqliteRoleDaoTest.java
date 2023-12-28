package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteRoleDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SqliteRoleDaoTest {
    @Test
    public void testGet() {
        Dao<Role, Integer> roleDao = new SqliteRoleDao();
        Role role = roleDao.get(1);
        assertEquals("admin", role.getRoleName());
    }
}
