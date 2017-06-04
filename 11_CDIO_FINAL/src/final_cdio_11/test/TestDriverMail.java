package final_cdio_11.test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import final_cdio_11.java.utils.MailHandler;

public class TestDriverMail {

	@Test
	public void test() {
		MailHandler mailobj = new MailHandler();
		try {
			mailobj.sendMail("trolund@gmail.com", "dette er en test", "Junit Test");
			mailobj.sendMail("iyyelsec@gmail.com", "dette er en test", "Junit Test");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}