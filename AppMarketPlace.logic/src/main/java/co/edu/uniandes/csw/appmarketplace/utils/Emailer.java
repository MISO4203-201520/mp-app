/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.appmarketplace.utils;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ca.forero10
 */
public class Emailer {
    
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    
    private static final String PORT = "587";
    private static final String PASSWORD = "4pp0t3c42015";
    private static final String FROM = "appoteca@gmail.com";
    
    
    private static void send(String to, String text, String subject) {
        try {		
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", PORT);
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);       
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            generateMailMessage.setSubject(subject);
            String emailBody = text;
            generateMailMessage.setContent(emailBody, "text/html"); 
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", FROM, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            System.out.println("Mail has been sent succesfully");
        } catch (Exception ex) {
            Logger.getLogger(Emailer.class.getName()).log(Level.SEVERE, null, ex);
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
