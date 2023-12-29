package com.itep.hust.aimsgroup.model.account;


import java.util.List;
import java.util.Objects;

public class Account {
    private Integer id;
    private String email;
    private String password;

    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String email, String password, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
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
        return Objects.equals(email, account.email) &&
                Objects.equals(password, account.password) &&
                roles.equals(account.getRoles());
    }
}
