package com.itep.hust.aimsgroup.service.email;

public class Sender {
    private final static Sender sender = new Sender();

    public static Sender getInstance() {
        return sender;
    }

    private boolean Auth = true;
    private boolean TLS = true;
    private String Host = "smtp.gmail.com";
    private String Port = "587";
    private String Email = "lethanhbinhkts@gmail.com";
    private String Password = "eeodnbsgxjeznklz";

    public boolean isAuth() {
        return Auth;
    }

    public void setAuth(boolean auth) {
        Auth = auth;
    }

    public boolean isTLS() {
        return TLS;
    }

    public void setTLS(boolean TLS) {
        this.TLS = TLS;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public String getPort() {
        return Port;
    }

    public void setPort(String port) {
        Port = port;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
