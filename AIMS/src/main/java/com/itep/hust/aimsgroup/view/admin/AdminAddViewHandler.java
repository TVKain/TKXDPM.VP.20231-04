package com.itep.hust.aimsgroup.view.admin;

import com.itep.hust.aimsgroup.controller.admin.AdminAddController;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteAccountDao;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteRoleDao;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.login.LoginViewHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckListView;

import java.util.List;

public class AdminAddViewHandler {


    @FXML
    private CheckListView<Role> roleCheckList;

    @FXML
    private Button backButton;

    @FXML
    private Button createAccountButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button logoutButton;

    @FXML
    private PasswordField passwordTextField;

    private final AdminAddController adminAddController = new AdminAddController(new SqliteAccountDao());

    @FXML
    public void initialize() {
        initializeButtons();
        initializeRoleCheckList();
    }

    public void initializeButtons() {
        backButton.setOnMouseClicked(e -> {
            Screen.setScreen("/fxml/admin/admin.fxml", new AdminViewHandler());
        });

        logoutButton.setOnMouseClicked(e -> {
            Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler());
        });

        createAccountButton.setOnMouseClicked(e -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            List<Role> roles = roleCheckList.getCheckModel().getCheckedItems().stream().toList();

            Account account = new Account(email, password, roles);

            if (!adminAddController.createAccount(account)) {
                return;
            }

            Screen.setScreen("/fxml/admin/admin-add.fxml", new AdminAddViewHandler());
        });
    }

    public void initializeRoleCheckList() {
        List<Role> roles = new SqliteRoleDao().getAll();

        roleCheckList.setItems(FXCollections.observableArrayList(roles));
        roleCheckList.setSelectionModel(null);
    }


}
