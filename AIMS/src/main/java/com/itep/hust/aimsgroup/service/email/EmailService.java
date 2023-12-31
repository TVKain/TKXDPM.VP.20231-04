package com.itep.hust.aimsgroup.service.email;

public abstract class EmailService {
    public void sendMail(String recipient, String subject, String content) {
        new Thread(() -> {
            send(recipient, subject, content);
        }).start();
    }
    public abstract void send(String recipient, String subject, String content);
}
