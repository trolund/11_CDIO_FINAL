package final_cdio_11.test.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.validator.IOperatorValidator;
import final_cdio_11.java.data.validator.OperatorValidator;

public class TestOperatorValidator {

	private IOperatorValidator oprValidator;

	@Before
	public void setUp() {
		oprValidator = new OperatorValidator();
	}

	@After
	public void tearDown() {
		oprValidator = null;
	}

	/*
	 * oprIdIsValid tests.
	 */
	@Test
	public void testIsOprIdValidPositive00() {
		int oprId = 2374224;

		boolean actual = oprValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidPositive01() {
		int oprId = 1;

		boolean actual = oprValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidPositive02() {
		int oprId = 99999999;

		boolean actual = oprValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative00() {
		int oprId = 942034029;

		boolean actual = oprValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative01() {
		int oprId = 0;

		boolean actual = oprValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative02() {
		int oprId = -1;

		boolean actual = oprValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isOprNameValid tests.
	 */
	@Test
	public void testIsOprNameValidPositive00() {
		String oprName = "Jackson";

		boolean actual = oprValidator.isOprNameValid(oprName);
		boolean expected = true;

		assertEquals("Failed: " + oprName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidPositive01() {
		String oprName = "JJ";

		boolean actual = oprValidator.isOprNameValid(oprName);
		boolean expected = true;

		assertEquals("Failed: " + oprName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidPositive02() {
		String oprName = "JackJackJackJackJack";

		boolean actual = oprValidator.isOprNameValid(oprName);
		boolean expected = true;

		assertEquals("Failed: " + oprName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidNegative00() {
		String oprName = null;

		boolean actual = oprValidator.isOprNameValid(oprName);
		boolean expected = false;

		assertEquals("Failed: " + oprName + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidNegative01() {
		String oprName = "x";

		boolean actual = oprValidator.isOprNameValid(oprName);
		boolean expected = false;

		assertEquals("Failed: " + oprName + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidNegative02() {
		String oprName = "JackJackJackJackJackJackJackJackJackJack";

		boolean actual = oprValidator.isOprNameValid(oprName);
		boolean expected = false;

		assertEquals("Failed: " + oprName + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isOprIniValid tests.
	 */
	@Test
	public void testIsOprIniValidPositive00() {
		String oprIni = "22";

		boolean actual = oprValidator.isOprIniValid(oprIni);
		boolean expected = true;

		assertEquals("Failed: " + oprIni + " is supposed to be valid.", expected, actual);
	}

	public void testIsOprIniValidPositive01() {
		String oprIni = "222";

		boolean actual = oprValidator.isOprIniValid(oprIni);
		boolean expected = true;

		assertEquals("Failed: " + oprIni + " is supposed to be valid.", expected, actual);
	}

	public void testIsOprIniValidPositive02() {
		String oprIni = "2222";

		boolean actual = oprValidator.isOprIniValid(oprIni);
		boolean expected = true;

		assertEquals("Failed: " + oprIni + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIniValidNegative00() {
		String oprIni = "1";

		boolean actual = oprValidator.isOprIniValid(oprIni);
		boolean expected = false;

		assertEquals("Failed: " + oprIni + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIniValidNegative01() {
		String oprIni = "1111111111";

		boolean actual = oprValidator.isOprIniValid(oprIni);
		boolean expected = false;

		assertEquals("Failed: " + oprIni + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIniValidNegative02() {
		String oprIni = "1111111111111111111111111";

		boolean actual = oprValidator.isOprIniValid(oprIni);
		boolean expected = false;

		assertEquals("Failed: " + oprIni + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isOprEmailValid test.
	 */
	@Test
	public void testIsOprEmailValidPositive00() {
		String oprEmail = "t.mascagni@gmail.com";

		boolean actual = oprValidator.isOprEmailValid(oprEmail);
		boolean expected = true;

		assertEquals("Failed: " + oprEmail + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprEmailValidPositive01() {
		String oprEmail = "iyyelsec@gmail.com";

		boolean actual = oprValidator.isOprEmailValid(oprEmail);
		boolean expected = true;

		assertEquals("Failed: " + oprEmail + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprEmailValidPositive02() {
		String oprEmail = "b.farazan@gmail.com";

		boolean actual = oprValidator.isOprEmailValid(oprEmail);
		boolean expected = true;

		assertEquals("Failed: " + oprEmail + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprEmailValidNegative00() {
		String oprEmail = "1111111111111111111111111@fail.aes";

		boolean actual = oprValidator.isOprIniValid(oprEmail);
		boolean expected = false;

		assertEquals("Failed: " + oprEmail + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprEmailValidNegative01() {
		String oprEmail = "iRplebl0rd@haxor.1337";

		boolean actual = oprValidator.isOprIniValid(oprEmail);
		boolean expected = false;

		assertEquals("Failed: " + oprEmail + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprEmailValidNegative02() {
		String oprEmail = "Zygsej0102@irzyg.0l123";

		boolean actual = oprValidator.isOprIniValid(oprEmail);
		boolean expected = false;

		assertEquals("Failed: " + oprEmail + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isOprCprValid test.
	 */
	@Test
	public void testIsOprCprValidPositive00() {
		String oprCpr = "0208931111";

		boolean actual = oprValidator.isOprCprValid(oprCpr);
		boolean expected = true;

		assertEquals("Failed: " + oprCpr + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprCprValidPositive01() {
		String oprCpr = "0000000000";

		boolean actual = oprValidator.isOprCprValid(oprCpr);
		boolean expected = true;

		assertEquals("Failed: " + oprCpr + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprCprValidPositive02() {
		String oprCpr = "9999999999";

		boolean actual = oprValidator.isOprCprValid(oprCpr);
		boolean expected = true;

		assertEquals("Failed: " + oprCpr + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprCprValidNegative00() {
		String oprCpr = "";

		boolean actual = oprValidator.isOprCprValid(oprCpr);
		boolean expected = false;

		assertEquals("Failed: " + oprCpr + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprCprValidNegative01() {
		String oprCpr = "asd";

		boolean actual = oprValidator.isOprCprValid(oprCpr);
		boolean expected = false;

		assertEquals("Failed: " + oprCpr + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprCprValidNegative02() {
		String oprCpr = "11111111111111111111111111111111111";

		boolean actual = oprValidator.isOprCprValid(oprCpr);
		boolean expected = false;

		assertEquals("Failed: " + oprCpr + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isOprPasswordValid test.
	 */
	@Test
	public void testIsOprPasswordValidPositive00() {
		String oprPassword = "11111";

		boolean actual = oprValidator.isOprPasswordValid(oprPassword);
		boolean expected = true;

		assertEquals("Failed: " + oprPassword + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprPasswordValidPositive01() {
		String oprPassword = "111111111111111111111111111111";

		boolean actual = oprValidator.isOprPasswordValid(oprPassword);
		boolean expected = true;

		assertEquals("Failed: " + oprPassword + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprPasswordValidPositive02() {
		String oprPassword = "111ojsfdgpojdfg11";

		boolean actual = oprValidator.isOprPasswordValid(oprPassword);
		boolean expected = true;

		assertEquals("Failed: " + oprPassword + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprPasswordValidNegative00() {
		String oprPassword = "";

		boolean actual = oprValidator.isOprPasswordValid(oprPassword);
		boolean expected = false;

		assertEquals("Failed: " + oprPassword + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprPasswordValidNegative01() {
		String oprPassword = "jjjjjjjjjjttttttttttggggggggggqqqqqqqqqq";

		boolean actual = oprValidator.isOprPasswordValid(oprPassword);
		boolean expected = false;

		assertEquals("Failed: " + oprPassword + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprPasswordValidNegative02() {
		String oprPassword = "keklordpelbalmightymaster007-kekplebimba1337";

		boolean actual = oprValidator.isOprPasswordValid(oprPassword);
		boolean expected = false;

		assertEquals("Failed: " + oprPassword + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isOprStatusValid test.
	 */
	@Test
	public void testIsOprStatusValidPositive00() {
		int oprStatus = 0;

		boolean actual = oprValidator.isOprStatusValid(oprStatus);
		boolean expected = true;

		assertEquals("Failed: " + oprStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprStatusValidPositive01() {
		int oprStatus = 1;

		boolean actual = oprValidator.isOprStatusValid(oprStatus);
		boolean expected = true;

		assertEquals("Failed: " + oprStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprStatusValidFalse00() {
		int oprStatus = 3;

		boolean actual = oprValidator.isOprStatusValid(oprStatus);
		boolean expected = false;

		assertEquals("Failed: " + oprStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprValidPositive() {
		OperatorDTO oprDTO = new OperatorDTO(932, "Jack", "Jackensang", "JJS", "jjs@jjs.com", "0911985463", "whatisthisjackensang", 0);
		
		boolean actual = oprValidator.isOprValid(oprDTO);
		boolean expected = true;
		
		assertEquals("Failed: " + oprDTO + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsOprValidNegativeEmail() {
		OperatorDTO oprDTO = new OperatorDTO(932, "Jack", "Jackensang", "JJS", "jjsjjs.com", "0911985463", "whatisthisjackensang", 0);
		
		boolean actual = oprValidator.isOprValid(oprDTO);
		boolean expected = false;
		
		assertEquals("Failed: " + oprDTO + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsOprValidNegativeCpr() {
		OperatorDTO oprDTO = new OperatorDTO(932, "Jack", "Jackensang", "JJS", "jjs@jjs.com", "09119-85463", "whatisthisjackensang", 0);
		
		boolean actual = oprValidator.isOprValid(oprDTO);
		boolean expected = false;
		
		assertEquals("Failed: " + oprDTO + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsOprValidNegativePassword() {
		OperatorDTO oprDTO = new OperatorDTO(932, "Jack", "Jackensang", "JJS", "jjs@jjs.com", "0911985463", "bad", 0);
		
		boolean actual = oprValidator.isOprValid(oprDTO);
		boolean expected = false;
		
		assertEquals("Failed: " + oprDTO + " is supposed to be invalid.", expected, actual);
	}

}