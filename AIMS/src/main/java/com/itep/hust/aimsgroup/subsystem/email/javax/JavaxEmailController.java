package com.itep.hust.aimsgroup.subsystem.email.javax;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaxEmailController {
    protected Message prepareMessage(String subject, String recipient, String htmlCode) {
        try {
            Properties properties = getProperties();

            String myAccountEmail = JavaxEmailConfig.getInstance().getEmail();
            String password = JavaxEmailConfig.getInstance().getPassword();

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaxEmailSubsystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    protected void sendMessage(Message message) {
        System.out.println("Preparing to send email");
        try {
            Transport.send(message);

            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    protected Properties getProperties() {
        Properties properties = new Properties();
        //Enable authentication
        properties.put("mail.smtp.auth", String.valueOf(JavaxEmailConfig.getInstance().isAuth()));
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", String.valueOf(JavaxEmailConfig.getInstance().isTLS()));
        //Set SMTP host
        properties.put("mail.smtp.host", JavaxEmailConfig.getInstance().getHost());
        //Set smtp port
        properties.put("mail.smtp.port", JavaxEmailConfig.getInstance().getPort());
        return properties;
    }


}
