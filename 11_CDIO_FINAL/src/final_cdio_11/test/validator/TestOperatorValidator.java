package final_cdio_11.test.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

}