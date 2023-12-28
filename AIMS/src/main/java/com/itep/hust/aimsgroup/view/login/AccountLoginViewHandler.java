package com.itep.hust.aimsgroup.view.login;

import com.itep.hust.aimsgroup.controller.AccountLoginController;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAccountDao;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteRoleDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.admin.AdminViewHandler;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountLoginViewHandler {
    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordLabel;

    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    private TextField usernameLabel;

    private final Map<String, Pair<String, Object>> roleScreenMap = new HashMap<>();

    public AccountLoginViewHandler() {
        roleScreenMap.put("admin", new Pair<>("/fxml/admin/admin.fxml", new AdminViewHandler()));
        roleScreenMap.put("manager", new Pair<>("/fxml/manager/manager.fxml", new ManagerViewHandler()));
    }

    private final AccountLoginController accountLoginController = new AccountLoginController();


    @FXML
    public void initialize() {
        initializeComboBox();
        initializeLoginButton();
    }

    public void initializeLoginButton() {
        loginButton.setOnMouseClicked(e -> {
            String username = usernameLabel.getText();
            String password = passwordLabel.getText();
            Role role = roleComboBox.getValue();

            Account account = new Account(username, password);

            if (!accountLoginController.authenticateLogin(account, role,  new SqliteAccountDao())) {
                Popup.showError("Login fail");
                return;
            }


            Pair<String, Object> screen = roleScreenMap.get(role.getRoleName());
            Screen.setScreen(screen.getKey(), screen.getValue());
        });
    }

    public void initializeComboBox() {
        List<Role> roles = accountLoginController.getRoles(new SqliteRoleDao());

        roleComboBox.getItems().addAll(roles);

        roleComboBox.getSelectionModel().selectFirst();

        roleComboBox.setConverter(new StringConverter<Role>() {
            @Override
            public String toString(Role role) {
                return role == null ? null : role.getRoleName().toUpperCase();
            }

            @Override
            public Role fromString(String s) {
                for (Role role : roles) {
                    if (role.getRoleName().equals(s)) {
                        return role;
                    }
                }
                return null;
            }
        });
    }
}
