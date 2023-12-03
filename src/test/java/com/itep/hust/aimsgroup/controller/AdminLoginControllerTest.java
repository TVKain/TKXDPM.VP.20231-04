package com.itep.hust.aimsgroup.controller;

import com.itep.hust.aimsgroup.model.admin.Admin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AdminLoginControllerTest {
    AdminLoginController adminLoginController = new AdminLoginController();
    @Test
    public void testLogin() {
        Admin admin = new Admin();
        admin.setUsername("khanhtv");
        admin.setPassword("1");
        assertTrue(adminLoginController.authenticateLogin(admin));
    }
}
