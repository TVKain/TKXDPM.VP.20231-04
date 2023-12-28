package com.itep.hust.aimsgroup.model.account;


import java.util.Objects;

public class Account {
    private String username;
    private String password;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account() {

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        Account account = (Account) obj;
        return Objects.equals(username, account.username) &&
                Objects.equals(password, account.password) &&
                role.equals(account.getRole());
    }
}
