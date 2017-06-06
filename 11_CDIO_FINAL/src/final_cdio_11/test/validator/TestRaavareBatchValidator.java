package final_cdio_11.test.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.validator.IRaavareBatchValidator;
import final_cdio_11.java.data.validator.RaavareBatchValidator;

public class TestRaavareBatchValidator {
	private IRaavareBatchValidator raavareBatchValidator;

	@Before
	public void setUp() throws Exception {
		raavareBatchValidator = new RaavareBatchValidator();
	}

	@After
	public void tearDown() throws Exception {
		raavareBatchValidator = null;
	}

	/*
	 * RbIdValid tests.
	 */
	@Test
	public void testIsRbIdValidPositive00() {
		int rbId = 2374224;

		boolean actual = raavareBatchValidator.isRbIdValid(rbId);
		boolean expected = true;

		assertEquals("Failed: " + rbId+ " is supposed to be valid.", expected, actual);
	}
	@Test
	public void testIsRbIdValidPositive01() {
		int rbId = 1;

		boolean actual = raavareBatchValidator.isRbIdValid(rbId);
		boolean expected = true;

		assertEquals("Failed: " + rbId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRbIdValidPositive02() {
		int rbId = 99999999;

		boolean actual = raavareBatchValidator.isRbIdValid(rbId);
		boolean expected = true;

		assertEquals("Failed: " + rbId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRbIdValidNegative00() {
		int rbId = 942034029;

		boolean actual = raavareBatchValidator.isRbIdValid(rbId);
		boolean expected = false;

		assertEquals("Failed: " + rbId+ " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsRbIdValidNegative01() {
		int rbId = 0;

		boolean actual = raavareBatchValidator.isRbIdValid(rbId);
		boolean expected = false;

		assertEquals("Failed: " + rbId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsRbIdValidNegative02() {
		int rbId = -1;

		boolean actual = raavareBatchValidator.isRbIdValid(rbId);
		boolean expected = false;

		assertEquals("Failed: " + rbId + " is supposed to be invalid.", expected, actual);
	}


	/*
	 * RaavareIdValid tests.
	 */
	@Test
	public void testIsRaavareIdValidPositive00() {
		int raavareId = 2374224;

		boolean actual = raavareBatchValidator.isRaavareIdValid(raavareId);
		boolean expected = true;

		assertEquals("Failed: " + raavareId+ " is supposed to be valid.", expected, actual);
	}
	@Test
	public void testIsRaavareIdValidPositive01() {
		int raavareId = 1;

		boolean actual = raavareBatchValidator.isRaavareIdValid(raavareId);
		boolean expected = true;

		assertEquals("Failed: " + raavareId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidPositive02() {
		int raavareId = 99999999;

		boolean actual = raavareBatchValidator.isRaavareIdValid(raavareId);
		boolean expected = true;

		assertEquals("Failed: " + raavareId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidNegative00() {
		int raavareId = 942034029;

		boolean actual = raavareBatchValidator.isRaavareIdValid(raavareId);
		boolean expected = false;

		assertEquals("Failed: " + raavareId+ " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidNegative01() {
		int raavareId= 0;

		boolean actual = raavareBatchValidator.isRaavareIdValid(raavareId);
		boolean expected = false;

		assertEquals("Failed: " + raavareId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsRaavareIdValidNegative02() {
		int raavareId = -21;

		boolean actual = raavareBatchValidator.isRaavareIdValid(raavareId);
		boolean expected = false;

		assertEquals("Failed: " + raavareId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * AmountValid tests.
	 */
	@Test
	public void testIsAmountValidPositive00() {
		int amount = 0;

		boolean actual = raavareBatchValidator.isAmountValid(amount);
		boolean expected = true;

		assertEquals("Failed: " + amount+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsAmountValidPositive01() {
		int amount = 99999999;

		boolean actual = raavareBatchValidator.isAmountValid(amount);
		boolean expected = true;

		assertEquals("Failed: " + amount+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsAmountValidPositive02() {
		int amount = -1+1;

		boolean actual = raavareBatchValidator.isAmountValid(amount);
		boolean expected = true;

		assertEquals("Failed: " + amount+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsAmountValidNegative00() {
		int amount = -1;

		boolean actual = raavareBatchValidator.isAmountValid(amount);
		boolean expected = false;

		assertEquals("Failed: " + amount+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsAmountValidNegative01() {
		int amount = 558865896;

		boolean actual = raavareBatchValidator.isAmountValid(amount);
		boolean expected = false;

		assertEquals("Failed: " + amount+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsAmountValidNegative02() {
		int amount = -5;

		boolean actual = raavareBatchValidator.isAmountValid(amount);
		boolean expected = false;

		assertEquals("Failed: " + amount+ " is supposed to be valid.", expected, actual);
	}

	/*
	 * RbStatusValid tests.
	 */
	@Test
	public void testIsRbStatusValidPositive00() {
		int status = 0;

		boolean actual = raavareBatchValidator.isRbStatusValid(status);
		boolean expected = true;

		assertEquals("Failed: " + status+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRbStatusValidPositive01() {
		int status = 0;

		boolean actual = raavareBatchValidator.isRbStatusValid(status);
		boolean expected = true;

		assertEquals("Failed: " + status+ " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsRbStatusValidNegative00() {

		int status = 2;

		boolean actual = raavareBatchValidator.isRbStatusValid(status);
		boolean expected = false;

		assertEquals("Failed: " + status+ " is supposed to be valid.", expected, actual);
	}
	@Test
	public void testIsRbStatusValidNegative01() {

		int status = -1;

		boolean actual = raavareBatchValidator.isRbStatusValid(status);
		boolean expected = false;

		assertEquals("Failed: " + status+ " is supposed to be valid.", expected, actual);
	}

}


