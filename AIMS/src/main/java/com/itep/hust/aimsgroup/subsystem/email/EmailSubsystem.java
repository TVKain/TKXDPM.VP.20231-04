package com.itep.hust.aimsgroup.subsystem.email;

public abstract class EmailSubsystem {
    public void sendMail(String recipient, String subject, String content) {
        new Thread(() -> {
            send(recipient, subject, content);
        }).start();
    }
    public abstract void send(String recipient, String subject, String content);
}
