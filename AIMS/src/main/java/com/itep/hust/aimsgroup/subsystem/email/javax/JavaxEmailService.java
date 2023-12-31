package com.itep.hust.aimsgroup.subsystem.email.javax;

import com.itep.hust.aimsgroup.subsystem.email.EmailService;

import javax.mail.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JavaxEmailService extends EmailService {
    @Override
    public void send(String recipient, String subject, String content) {
        System.out.println("Preparing to send email");
        Properties properties = getProperties();

        //Your gmail address
        String myAccountEmail = JavaxEmailConfig.getInstance().getEmail();
        //Your gmail password
        String password = JavaxEmailConfig.getInstance().getPassword();

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, subject, recipient, content);


        try {
            Transport.send(message);

            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties getProperties() {
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

    private Message prepareMessage(Session session, String myAccountEmail, String subject, String recipient, String htmlCode) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaxEmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
