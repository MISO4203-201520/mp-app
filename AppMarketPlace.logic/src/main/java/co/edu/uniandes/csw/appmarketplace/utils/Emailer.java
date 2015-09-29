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

    private static final String PORT = System.getenv("EMAIL_PORT");
    private static final String PASSWORD = System.getenv("EMAIL_HOST_PASSWORD");
    private static final String FROM = System.getenv("EMAIL_HOST_USER");
    private static final String USE_TLS = System.getenv("EMAIL_USE_TLS");
    private static final String HOST = System.getenv("EMAIL_HOST");

    private static void send(String to, String text, String subject) {
        try {
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", PORT);
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", USE_TLS);
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            generateMailMessage.setSubject(subject);
            String emailBody = text;
            generateMailMessage.setContent(emailBody, "text/html");
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect(HOST, FROM, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            System.out.println("Mail has been sent succesfully");
        } catch (Exception ex) {
            Logger.getLogger(Emailer.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error mail");

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
            final String clientEmail, final String app) {
        final String message
                = "Hi <b>" + dev + "</b>"
                + "<br>"
                + "User client </b>" + client + "</b> has sent you a question about your app <b>" + app + "</b>."
                + "<br>"
                + "\"" + question + "\" sent:" + (date)
                + "<br>"
                + "If you want to response, you can send an email directly to <b>" + clientEmail + "</b>";
        System.out.println("Send email to " + devEmail + " " + message);
        new Thread() {
            @Override
            public void run() {
                send(devEmail, message, "New question in your apps in Appoteca");
            }
        }.start();
    }

    public static void sendPaymentEmail(final String user, final String userEmail,
            final String cost, final Date date,
            final String app) {
        final String message
                = "Hi <b>" + user + "</b>"
                + "<br>"
                + "</b>" + "</b> Your payment was successfull, you just bought <b>" + app + " applications" + "</b>."
                + "<br>"
                + "the cost of the transaction was <b>" + cost + "</b>" + " The date was: " + "<br>" + (date) + "</b>"
                + "<br>" + "We hope you enjoy your applications.";
        System.out.println("Send email to " + userEmail + " " + message);
        new Thread() {
            @Override
            public void run() {
                send(userEmail, message, "Thanks for your payment");
            }
        }.start();
    }
}
