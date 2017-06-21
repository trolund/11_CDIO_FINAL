package final_cdio_11.test.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.validator.IProductBatchCompValidator;
import final_cdio_11.java.data.validator.ProductBatchCompValidator;

public class TestProduktBatchCompValidator {

	private IProductBatchCompValidator pbcValidator;

	@Before
	public void setUp() throws Exception {
		pbcValidator = new ProductBatchCompValidator();
	}

	@After
	public void tearDown() throws Exception {
		pbcValidator = null;
	}

	/*
	 * productBatchIdValid test.
	 */

	@Test
	public void testIsproBatIdValidPositive00() {
		int proBatId = 1;

		boolean actual = pbcValidator.isPbIdValid(proBatId);
		boolean expected = true;

		assertEquals("Failed: " + proBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidPositive01() {
		int proBatId = 99999999;

		boolean actual = pbcValidator.isPbIdValid(proBatId);
		boolean expected = true;

		assertEquals("Failed: " + proBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidPositive02() {
		int proBatId = 123;

		boolean actual = pbcValidator.isPbIdValid(proBatId);
		boolean expected = true;

		assertEquals("Failed: " + proBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidNegative00() {
		int proBatId = 0;

		boolean actual = pbcValidator.isPbIdValid(proBatId);
		boolean expected = false;

		assertEquals("Failed: " + proBatId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidNegative01() {
		int proBatId = 942034029;

		boolean actual = pbcValidator.isPbIdValid(proBatId);
		boolean expected = false;

		assertEquals("Failed: " + proBatId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidNegative02() {
		int proBatId = -1;

		boolean actual = pbcValidator.isPbIdValid(proBatId);
		boolean expected = false;

		assertEquals("Failed: " + proBatId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * raaBatIdValid test
	 */

	@Test
	public void testIsraaBatIdValidPositive00() {
		int raaBatId = 1;

		boolean actual = pbcValidator.isPbIdValid(raaBatId);
		boolean expected = true;

		assertEquals("Failed: " + raaBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsraaBatIdValidPositive01() {
		int raaBatId = 99999999;

		boolean actual = pbcValidator.isPbIdValid(raaBatId);
		boolean expected = true;

		assertEquals("Failed: " + raaBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproraaIdValidPositive02() {
		int raaBatId = 123;

		boolean actual = pbcValidator.isPbIdValid(raaBatId);
		boolean expected = true;

		assertEquals("Failed: " + raaBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsraaBatIdValidNegative00() {
		int raaBatId = 0;

		boolean actual = pbcValidator.isPbIdValid(raaBatId);
		boolean expected = false;

		assertEquals("Failed: " + raaBatId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsraaBatIdValidNegative01() {
		int raaBatId = 942034029;

		boolean actual = pbcValidator.isPbIdValid(raaBatId);
		boolean expected = false;

		assertEquals("Failed: " + raaBatId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsraaBatIdValidNegative02() {
		int raaBatId = -1;

		boolean actual = pbcValidator.isPbIdValid(raaBatId);
		boolean expected = false;

		assertEquals("Failed: " + raaBatId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isTaraValid test
	 * Negativ test?
	 */

	@Test
	public void testIsTareValidPositive00() {
		double isTare = -200.222;

		boolean actual = pbcValidator.isTareValid(isTare);
		boolean expected = true;

		assertEquals("Failed: " + isTare + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsTareValidPositive01() {
		int isTare = 1;

		boolean actual = pbcValidator.isTareValid(isTare);
		boolean expected = true;

		assertEquals("Failed: " + isTare + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsTareValidPositive02() {
		long isTare = 1124321354L;

		boolean actual = pbcValidator.isTareValid(isTare);
		boolean expected = true;

		assertEquals("Failed: " + isTare + " is supposed to be valid.", expected, actual);
	}

	/*
	 * isNettoValid test
	 */

	@Test
	public void testIsNettoValidPositive00() {
		double isNetto = -200.222;

		boolean actual = pbcValidator.isNettoValid(isNetto);
		boolean expected = true;

		assertEquals("Failed: " + isNetto + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsNettoValidPositive01() {
		int isNetto = 1;

		boolean actual = pbcValidator.isNettoValid(isNetto);
		boolean expected = true;

		assertEquals("Failed: " + isNetto + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsNettoValidPositive02() {
		long isNetto = 1124321354L;

		boolean actual = pbcValidator.isNettoValid(isNetto);
		boolean expected = true;

		assertEquals("Failed: " + isNetto + " is supposed to be valid.", expected, actual);
	}

	/*
	 * oprIdIsValid tests.
	 */
	@Test
	public void testIsOprIdValidPositive00() {
		int oprId = 2374224;

		boolean actual = pbcValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidPositive01() {
		int oprId = 1;

		boolean actual = pbcValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidPositive02() {
		int oprId = 99999999;

		boolean actual = pbcValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative00() {
		int oprId = 942034029;

		boolean actual = pbcValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative01() {
		int oprId = 0;

		boolean actual = pbcValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative02() {
		int oprId = -1;

		boolean actual = pbcValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isPBCStatusValid test.
	 */
	@Test
	public void testIsPbcStatusValidPositive00() {
		int oprStatus = 0;

		boolean actual = pbcValidator.isPbcStatusValid(oprStatus);
		boolean expected = true;

		assertEquals("Failed: " + oprStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidPositive01() {
		int oprStatus = 1;

		boolean actual = pbcValidator.isPbcStatusValid(oprStatus);
		boolean expected = true;

		assertEquals("Failed: " + oprStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidFalse00() {
		int oprStatus = 3;

		boolean actual = pbcValidator.isPbcStatusValid(oprStatus);
		boolean expected = false;

		assertEquals("Failed: " + oprStatus + " is supposed to be valid.", expected, actual);
	}

}