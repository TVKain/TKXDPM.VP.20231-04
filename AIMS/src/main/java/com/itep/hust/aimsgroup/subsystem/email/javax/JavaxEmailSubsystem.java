package com.itep.hust.aimsgroup.subsystem.email.javax;

import com.itep.hust.aimsgroup.subsystem.email.EmailSubsystem;

import javax.mail.*;


public class JavaxEmailSubsystem extends EmailSubsystem {
    private final JavaxEmailController javaxEmailController = new JavaxEmailController();
    @Override
    public void send(String recipient, String subject, String content) {
        Message message = javaxEmailController.prepareMessage(subject, recipient, content);
        javaxEmailController.sendMessage(message);
    }

}
