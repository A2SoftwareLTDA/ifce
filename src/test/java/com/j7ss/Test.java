package com.j7ss;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Test {

	
	public static void main(String[] args) throws UnsupportedEncodingException {

        final String ZOHO_HOST = "smtp.zoho.com";
        final String TLS_PORT = "897";
 
        final String SENDER_EMAIL = "contact@j7ss.com";
        final String SENDER_USERNAME = "contact@j7ss.com";
        final String SENDER_PASSWORD = "@aqswde123";
 
        // protocol properties
        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", ZOHO_HOST); // change to GMAIL_HOST for gmail                                                         // for gmail
        props.setProperty("mail.smtp.port", TLS_PORT);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
 
        Session session = Session.getInstance(props, null);
 
        try {
            // create the message
            final MimeMessage msg = new MimeMessage(session);
 
            // set recipients and content
            msg.setFrom(new InternetAddress(SENDER_EMAIL));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("edivando7@gmail.com", false));
            msg.setSubject("Demo Server");
            msg.setText("Message Sent via JavaMail", "utf-8", "html");
            msg.setSentDate(new Date());
 
            // this means you do not need socketFactory properties
            Transport transport = session.getTransport("smtps");
 
            // send the mail
            transport.connect(ZOHO_HOST, SENDER_USERNAME, SENDER_PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
 
        } catch (MessagingException e) {
//            logger.log(Level.SEVERE, "Failed to send message", e);
 
        }
	}
}
