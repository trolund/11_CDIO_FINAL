package final_cdio_11.java.utils;
 
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.*;

import final_cdio_11.java.data.FileHandler;
 
public class SendEmail {
	
	private FileHandler fileHandler;
	   
	  public void mail(String to, String msg, String sub) throws AddressException, MessagingException{
		  
	fileHandler = FileHandler.getInstance();
	   
      // Recipient's email ID needs to be mentioned.

      // Sender's email ID needs to be mentioned
      String from = "DTUgruppe11@gmail.com";
      final String username = fileHandler.mailSQL("USERNAME");//change accordingly
      final String password = fileHandler.mailSQL("PASSWORD");//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = fileHandler.mailSQL("HOST");

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

  
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject(sub);
	
	   // Now set the actual message
	   message.setText(msg);

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

	  }
   
   
   
}