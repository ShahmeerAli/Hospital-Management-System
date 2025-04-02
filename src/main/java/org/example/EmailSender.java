package org.example;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;


public class EmailSender {
    public static void EmailNotification(String to, String subject, String message) {
        System.out.println("Preparing to send the Mail");


        String From = "qshahmeer647@gmail.com";
        sendEmail(message, subject, to, From);


    }

    private static void sendEmail(String message, String subject, String to, String from) {
        //setting the host  server Variable

        String host = "smtp.gmail.com";
        //Getting the system properties
        Properties properties = System.getProperties();
        //setting the host
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        //setting the session
        final String username = "qshahmeer647@gmail.com";
        final String password = "peltmocakxskdmel";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        //SENDING THE MESSAGE

        try {
            MimeMessage m = new MimeMessage(session);
            m.setFrom(from);
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding the subject
            m.setSubject(subject);
            //adding the message

            m.setText(message);

            //sending the message finally

            Transport.send(m);

            System.out.println("EMAIL SENT SUCCESSFULLY");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}