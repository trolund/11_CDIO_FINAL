package final_cdio_11.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import final_cdio_11.java.utils.SendEmail;

public class mailtest {

	@Test
	public void newPasswordMailtest() {
		
		SendEmail udbringer = new SendEmail();
		
		udbringer.mail("trolund@gmail.com", "test Mail", "TEST");
		
	}

}
