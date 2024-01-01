package com.itep.hust.aimsgroup.subsystem.email;

public abstract class EmailSubsystem {
    public final void sendMail(String recipient, String subject, String content) {
        new Thread(() -> {
            send(recipient, subject, content);
        }).start();
    }
    protected abstract void send(String recipient, String subject, String content);
}
