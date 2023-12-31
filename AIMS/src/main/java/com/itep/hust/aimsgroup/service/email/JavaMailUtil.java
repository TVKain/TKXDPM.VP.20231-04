package com.itep.hust.aimsgroup.service.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
    public static void sendMail(String recepient, String content) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", String.valueOf(Sender.getInstance().isAuth()));
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", String.valueOf(Sender.getInstance().isTLS()));
        //Set SMTP host
        properties.put("mail.smtp.host", Sender.getInstance().getEmail());
        //Set smtp port
        properties.put("mail.smtp.port", Sender.getInstance().getPort());

        //Your gmail address
        String myAccountEmail = Sender.getInstance().getEmail();
        //Your gmail password
        String password = Sender.getInstance().getPassword();

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, content);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String htmlCode) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("AIMS Receipt");
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
