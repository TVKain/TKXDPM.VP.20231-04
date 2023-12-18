package com.itep.hust.aimsgroup.controller;

import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAdminDao;
import com.itep.hust.aimsgroup.service.dao.Dao;


public class AdminLoginController {
    public boolean authenticateLogin(Admin admin, Dao<Admin, String> adminDao) {
        String username = admin.getUsername();
        String password = admin.getPassword();

        Admin adminDb = adminDao.get(username);

        if (adminDb == null) {
            return false;
        }

        return adminDb.getPassword().equals(password);
    }
}
