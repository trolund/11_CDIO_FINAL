package final_cdio_11.java.utils;

//File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

public void mail(String toMail, String subject, String msg) {    
   // Recipient's email ID needs to be mentioned.
   String to = toMail;

   // Sender's email ID needs to be mentioned
   String from = "RestAPI@DTUGruppe11.dk";

   // Assuming you are sending email from localhost
   String host = "localhost";

   // Get system properties
   Properties properties = System.getProperties();

   // Setup mail server
   properties.setProperty("mail.smtp.host", host);

   // Get the default Session object.
   Session session = Session.getDefaultInstance(properties);

   try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject(subject);

      // Now set the actual message
      message.setText(msg);

      // Send message
      Transport.send(message);
      System.out.println("Sent mail successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}
}