package com.itep.hust.aimsgroup.controller;

import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.service.dao.AdminDao;


public class AdminLoginController {
    public boolean authenticateLogin(Admin admin) {
        String username = admin.getUsername();
        String password = admin.getPassword();

        AdminDao adminDao = new AdminDao();

        Admin adminDb = adminDao.getByUsername(username);

        if (adminDb == null) {
            return false;
        }

        return adminDb.getPassword().equals(password);
    }
}
