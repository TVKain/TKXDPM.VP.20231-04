package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.controller.AdminLoginController;
import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAdminDao;
import com.itep.hust.aimsgroup.util.Popup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AdminLoginViewHandler {
    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField usernameLabel;

    private final AdminLoginController adminLoginController = new AdminLoginController();

    @FXML
    public void initialize() {
        loginButton.setOnMouseClicked(e -> {
            String username = usernameLabel.getText();
            String password = passwordLabel.getText();

            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);

            if (adminLoginController.authenticateLogin(admin, new SqliteAdminDao())) {
                Popup.showSuccess("Login success");
            } else {
                Popup.showError("Login failed: Invalid credentials");
            }
        });
    }

}
