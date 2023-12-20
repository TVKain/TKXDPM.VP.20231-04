package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAdminDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class SqliteAdminDaoTest {
    @Test
    public void getTest() {
        SqliteAdminDao sqliteAdminDao = new SqliteAdminDao();

        Admin admin = sqliteAdminDao.get("khanhtv");

        assertEquals("khanhtv", admin.getUsername());
        assertEquals("1", admin.getPassword());

    }
}
