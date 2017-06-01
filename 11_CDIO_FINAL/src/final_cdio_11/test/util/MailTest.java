package final_cdio_11.test.util;

import static org.junit.Assert.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.junit.Test;

import final_cdio_11.java.utils.SendEmail;

public class MailTest {

	@Test
	public void test() {
		
		SendEmail mailobj = new SendEmail();
		
		try {
			mailobj.mail("trolund@gmail.com", "dette er en test", "Junit Test");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
