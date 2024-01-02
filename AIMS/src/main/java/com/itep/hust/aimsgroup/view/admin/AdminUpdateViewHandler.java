package com.itep.hust.aimsgroup.view.admin;

import com.itep.hust.aimsgroup.controller.account.AccountController;
import com.itep.hust.aimsgroup.exception.account.UpdateAccountException;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteRoleDao;
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

public class AdminUpdateViewHandler {
    @FXML
    private CheckListView<Role> roleCheckList;

    @FXML
    private Button backButton;

    @FXML
    private Button updateAccountButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField passwordTextField;

    private final Account account;

    private final AccountController accountController;
    public AdminUpdateViewHandler(Account account, AccountController accountController) {
        this.account = account;
        this.accountController = accountController;
    }

    @FXML
    public void initialize() {
        initializeRoleCheckList();
        initializeFields();
        initializeButtons();
    }

    public void initializeFields() {
        emailTextField.setText(account.getEmail());
        passwordTextField.setText(account.getPassword());

        for (Role role : account.getRoles()) {
            roleCheckList.getCheckModel().check(role);
        }
    }

    public void initializeButtons() {
        backButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/admin/admin.fxml", new AdminViewHandler()));

        logoutButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler()));

        updateAccountButton.setOnMouseClicked(e -> {
            String email = emailTextField.getText();
            String password = passwordTextField.getText();
            List<Role> roles = roleCheckList.getCheckModel().getCheckedItems().stream().toList();

            try {
                accountController.updateAccount(account.getId(), email, password, roles, account.getStatus());
                Popup.showSuccess("Update account success");
            } catch (UpdateAccountException updateAccountException) {
                Popup.showError(updateAccountException.getMessage());
            }
        });
    }

    public void initializeRoleCheckList() {
        List<Role> roles = new SqliteRoleDao().getAll();

        roleCheckList.setItems(FXCollections.observableArrayList(roles));
        roleCheckList.setSelectionModel(null);
        roleCheckList.getCheckModel();
    }
}
