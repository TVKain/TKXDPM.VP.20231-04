package com.itep.hust.aimsgroup.dao.sqlite;

import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.Dao;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteRoleDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SqliteRoleDaoTest {
    @Test
    public void testGet() {
        Dao<Role, String> roleDao = new SqliteRoleDao();
        Role role = roleDao.get("admin");
        assertEquals("admin", role.getRoleName());
    }
}
