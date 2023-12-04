package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.service.dao.AdminDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AdminDaoTest {
    @Test
    public void getTest() {
        AdminDao adminDao = new AdminDao();

        Admin admin = adminDao.get("khanhtv");

        assertEquals("khanhtv", admin.getUsername());
        assertEquals("1", admin.getPassword());

    }
}
