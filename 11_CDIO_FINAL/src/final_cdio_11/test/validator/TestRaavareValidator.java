package final_cdio_11.test.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.validator.IRaavareValidator;
import final_cdio_11.java.data.validator.RaavareValidator;

public class TestRaavareValidator {
	private IRaavareValidator raavareValidator;

	@Before
	public void setUp() throws Exception {
		raavareValidator = new RaavareValidator();
	}

	@After
	public void tearDown() throws Exception {
		raavareValidator = null;
	}

	/*
	 * RaavareIdValidator tests.
	 */
	@Test
	public void testIsRaavareIdValidPositive00() {
		int raavareId = 619;

		boolean actual = raavareValidator.isRaavareIdValid(raavareId);
		boolean expected = true;

		assertEquals("Failed: " + raavareId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidPositive01() {
		int raavareId = 1;

		boolean actual = raavareValidator.isRaavareIdValid(raavareId);
		boolean expected = true;

		assertEquals("Failed: " + raavareId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidPositive02() {
		int raavareId = 97931697;

		boolean actual = raavareValidator.isRaavareIdValid(raavareId);
		boolean expected = true;

		assertEquals("Failed: " + raavareId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidNegative00() {
		int raavareId = 842039029;

		boolean actual = raavareValidator.isRaavareIdValid(raavareId);
		boolean expected = false;

		assertEquals("Failed: " + raavareId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidNegative01() {
		int raavareId = 00;

		boolean actual = raavareValidator.isRaavareIdValid(raavareId);
		boolean expected = false;

		assertEquals("Failed: " + raavareId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidNegative02() {
		int raavareId = -2;

		boolean actual = raavareValidator.isRaavareIdValid(raavareId);
		boolean expected = false;

		assertEquals("Failed: " + raavareId + " is supposed to be invalid.", expected, actual);
	}

}