package com.itep.hust.aimsgroup.view.admin;

import com.itep.hust.aimsgroup.controller.account.AccountController;
import com.itep.hust.aimsgroup.exception.account.CreateAccountException;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.login.LoginViewHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckListView;

import java.util.List;

public class AdminCreateViewHandler {


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
    private TextField passwordTextField;

    private final AccountController accountController;

    public AdminCreateViewHandler(AccountController accountController) {
        this.accountController = accountController;
    }

    @FXML
    public void initialize() {
        initializeButtons();
        initializeRoleCheckList();
    }

    public void initializeButtons() {
        backButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/admin/admin.fxml", new AdminViewHandler()));

        logoutButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler()));

        createAccountButton.setOnMouseClicked(e -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            List<Role> roles = roleCheckList.getCheckModel().getCheckedItems().stream().toList();

            try {
                accountController.createAccount(email, password, roles);
                Popup.showSuccess("Account created successfully");
                Screen.setScreen("/fxml/admin/admin-add.fxml", new AdminCreateViewHandler(accountController));
            } catch (CreateAccountException createAccountException) {
                Popup.showError(createAccountException.getMessage());
            }
        });
    }

    public void initializeRoleCheckList() {
        List<Role> roles = accountController.getAllRoles();

        roleCheckList.setItems(FXCollections.observableArrayList(roles));
        roleCheckList.setSelectionModel(null);
    }


}
