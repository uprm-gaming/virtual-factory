/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uprm.gaming.utils;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author David
 */
public class SendEmail {
    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_AUTH_USER = "yaguez.factory@gmail.com";
    private static final String SMTP_AUTH_PWD  = "#gamingYaguez$";
    
    public SendEmail(){
        
    }
    
    public String send(String emailTo, String playerName, String messageText){
        // Recipient's email ID needs to be mentioned.
        String to = emailTo;

        // Sender's email ID needs to be mentioned
        String from = SMTP_AUTH_USER;

        // Assuming you are sending email from localhost
        String host = SMTP_HOST_NAME;

        //authenticator
        Authenticator auth = new SMTPAuthenticator();
        
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.port", "587");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, auth);

        try{
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("ACCOUNT INFORMATION REQUEST for " + playerName.toUpperCase());
            // Now set the actual message
            message.setText(messageText);
            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(SMTP_HOST_NAME, 25, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.send(message);
            return "";
        }catch (MessagingException mex) {
            return "Error: " +mex.getMessage();
        }
    }
    
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
           String username = SMTP_AUTH_USER;
           String password = SMTP_AUTH_PWD;
           return new PasswordAuthentication(username, password);
        }
    }
}
