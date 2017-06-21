package final_cdio_11.java.handler;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import final_cdio_11.java.utils.Utils;

public class MailHandler {

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();
	private final FileHandler fileHandler = FileHandler.getInstance();

	/*
	 * Method used to send a mail.
	 */
	public void sendMail(String to, String msg, String sub) throws AddressException, MessagingException {

		/* Mail sender information */
		String from = textHandler.ADMIN_EMAIL;
		final String username = fileHandler.getProperty("MAIL_USERNAME");
		final String password = fileHandler.getProperty("MAIL_PASSWORD");
		final String port = fileHandler.getProperty("MAIL_PORT");
		final String host = fileHandler.getProperty("MAIL_HOST");

		/* Configuring mail properties. */
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		/* Get the Session object. */
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		/* Create a default MimeMessage object. */
		Message message = new MimeMessage(session);

		/* Set From: header field of the header. */
		message.setFrom(new InternetAddress(from));

		/* Set To: header field of the header. */
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

		/* Set Subject: header field. */
		message.setSubject(sub);

		/* Now set the actual message */
		message.setText(msg);

		/* Send message. */
		Transport.send(message);

		if (utils.DEV_ENABLED) utils.logMessage(textHandler.devMailSentMessage(to));
	}

}