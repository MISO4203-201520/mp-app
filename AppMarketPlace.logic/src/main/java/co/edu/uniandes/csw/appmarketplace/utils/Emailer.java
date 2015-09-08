/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ca.forero10
 */
public class Emailer {
    
    private static final String PORT = "465";
    private static final String USER = "appoteca";
    private static final String PASSWORD = "4pp0t3c42015";
    private static final String FROM = "appoteca@gmail.com";
    
    private static void send(String to, String text, String subject) {
    /*    Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", PORT);*/
        Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(USER, PASSWORD);
			}
		  });
        /*
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {                
                protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER,PASSWORD);
                }
        });*/

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM));
            message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Mail sent correctly");

        } catch (MessagingException e) {
                throw new RuntimeException(e);
        }
    }
    /**
     * Send an email when a clien make a question to developer
     * 
     * @param dev developer name
     * @param devEmail developer email
     * @param question question text
     * @param date question sent date 
     * @param client client name
     * @param clientEmail client email
     * @param app app name
     */
    public static void sendQuestionEmail(final String dev, final String devEmail, 
                    final String question, final Date date, final String client, 
                    final String clientEmail, final String app){
        final String message = 
                "Hi <b>"+dev+"</b>"
                + "<br>"
                + "User client </b>"+client+"</b> has sent you a question about your app <b>"+app+"</b>."
                + "<br>"
                + "\""+question+"\" sent:"+(date)
                + "<br>"
                + "If you want to response, you can send an email directly to <b>"+clientEmail+"</b>";
        System.out.println("Send email to "+devEmail+" "+message);
        new Thread(){
            @Override
            public void run() {
               send(devEmail, message, "New question in your apps in Appoteca"); 
            }            
        }.start();              
    }
            
}
