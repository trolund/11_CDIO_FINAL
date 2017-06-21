package final_cdio_11.test.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.validator.IReceptValidator;
import final_cdio_11.java.data.validator.ReceptValidator;

public class TestReceptValidator {

	private IReceptValidator receptValidator;

	@Before
	public void setUp() throws Exception {
		receptValidator = new ReceptValidator();
	}

	@After
	public void tearDown() throws Exception {
		receptValidator = null;
	}

	/*
	 * ReceptIdValidator tests.
	 */
	@Test
	public void testIsReceptIdValidPositive00() {
		int receptId = 23;

		boolean actual = receptValidator.isReceptIdValid(receptId);
		boolean expected = true;

		assertEquals("Failed: " + receptId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidPositive01() {
		int receptId = 1;

		boolean actual = receptValidator.isReceptIdValid(receptId);
		boolean expected = true;

		assertEquals("Failed: " + receptId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidPositive02() {
		int receptId = 99999999;

		boolean actual = receptValidator.isReceptIdValid(receptId);
		boolean expected = true;

		assertEquals("Failed: " + receptId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidNegative00() {
		int receptId = 842034029;

		boolean actual = receptValidator.isReceptIdValid(receptId);
		boolean expected = false;

		assertEquals("Failed: " + receptId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidNegative01() {
		int receptId = 0;

		boolean actual = receptValidator.isReceptIdValid(receptId);
		boolean expected = false;

		assertEquals("Failed: " + receptId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidNegative02() {
		int receptId = -1;

		boolean actual = receptValidator.isReceptIdValid(receptId);
		boolean expected = false;

		assertEquals("Failed: " + receptId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * ReceptNameValidtor tests.
	 */
	@Test
	public void testIsReceptNameValidPositive00() {
		String receptName = "Is";

		boolean actual = receptValidator.isReceptNameValid(receptName);
		boolean expected = true;

		assertEquals("Failed: " + receptName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidPositive01() {
		String receptName = "Saltvand";

		boolean actual = receptValidator.isReceptNameValid(receptName);
		boolean expected = true;

		assertEquals("Failed: " + receptName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprNameValidPositive02() {
		String receptName = "de conffit de canard";

		boolean actual = receptValidator.isReceptNameValid(receptName);
		boolean expected = true;

		assertEquals("Failed: " + receptName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptNameValidNegative00() {
		String receptName = "å";

		boolean actual = receptValidator.isReceptNameValid(receptName);
		boolean expected = false;

		assertEquals("Failed: " + receptName + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptNameValidNegative01() {
		String receptName = "Mousse au de Chocolat ";

		boolean actual = receptValidator.isReceptNameValid(receptName);
		boolean expected = false;

		assertEquals("Failed: " + receptName + " is supposed to be valid.", expected, actual);
	}

	/*
	 * ReceptStatusValidtor tests.
	 */
	@Test
	public void testIsReceptStatusValidPositive00() {
		int receptStatus = 0;

		boolean actual = receptValidator.isReceptStatusValid(receptStatus);
		boolean expected = true;

		assertEquals("Failed: " + receptStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptStatusValidPositive01() {
		int receptStatus = 1;

		boolean actual = receptValidator.isReceptStatusValid(receptStatus);
		boolean expected = true;

		assertEquals("Failed: " + receptStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptStatusValidNegative00() {

		int receptStatus = 3;

		boolean actual = receptValidator.isReceptStatusValid(receptStatus);
		boolean expected = false;

		assertEquals("Failed: " + receptStatus + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsReceptStatusValidNegative01() {

		int receptStatus = -1;

		boolean actual = receptValidator.isReceptStatusValid(receptStatus);
		boolean expected = false;

		assertEquals("Failed: " + receptStatus + " is supposed to be invalid.", expected, actual);
	}

}