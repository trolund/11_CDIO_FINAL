package final_cdio_11.test.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.validator.IReceptCompValidator;
import final_cdio_11.java.data.validator.ReceptCompValidator;

public class TestReceptCompValidator {
	
	private IReceptCompValidator recComValidator;

	@Before
	public void setUp() throws Exception {
		recComValidator = new ReceptCompValidator();
	}

	@After
	public void tearDown() throws Exception {
		recComValidator = null;
	}

	/*
	 * IsValidReceptId test
	 */
	
	@Test
	public void testIsValidReceptIdValidPositive00() {
		int recId = 1;

		boolean actual = recComValidator.isReceptIdValid(recId);
		boolean expected = true;

		assertEquals("Failed: " + recId + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsValidReceptIdValidPositive01() {
		int recId = 99999999;

		boolean actual = recComValidator.isReceptIdValid(recId);
		boolean expected = true;

		assertEquals("Failed: " + recId + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsValidReceptIdValidPositive02() {
		int recId = 123;

		boolean actual = recComValidator.isReceptIdValid(recId);
		boolean expected = true;

		assertEquals("Failed: " + recId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsValidReceptIdValidNegative00() {
		int recId = 0;

		boolean actual = recComValidator.isReceptIdValid(recId);
		boolean expected = false;

		assertEquals("Failed: " + recId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsValidReceptIdValidNegative01() {
		int recId = 942034029;

		boolean actual = recComValidator.isReceptIdValid(recId);
		boolean expected = false;

		assertEquals("Failed: " + recId + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsValidReceptIdValidNegative02() {
		int recId = -1;

		boolean actual = recComValidator.isReceptIdValid(recId);
		boolean expected = false;

		assertEquals("Failed: " + recId + " is supposed to be invalid.", expected, actual);
	}
	
	/*
	 * isValidRaavareId test
	 */
	@Test
	public void testIsValidRaavareIdValidPositive00() {
		int raaId = 1;

		boolean actual = recComValidator.isRaavareIdValid(raaId);
		boolean expected = true;

		assertEquals("Failed: " + raaId + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsValidRaavareIdValidPositive01() {
		int raaId = 99999999;

		boolean actual = recComValidator.isRaavareIdValid(raaId);
		boolean expected = true;

		assertEquals("Failed: " + raaId + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsValidRaavareIdValidPositive02() {
		int raaId = 123;

		boolean actual = recComValidator.isRaavareIdValid(raaId);
		boolean expected = true;

		assertEquals("Failed: " + raaId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsValidRaavareIdValidNegative00() {
		int raaId = 0;

		boolean actual = recComValidator.isRaavareIdValid(raaId);
		boolean expected = false;

		assertEquals("Failed: " + raaId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsValidRaavareIdValidNegative01() {
		int raaId = 942034029;

		boolean actual = recComValidator.isRaavareIdValid(raaId);
		boolean expected = false;

		assertEquals("Failed: " + raaId + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsValidRaavareIdValidNegative02() {
		int raaId = -1;

		boolean actual = recComValidator.isRaavareIdValid(raaId);
		boolean expected = false;

		assertEquals("Failed: " + raaId + " is supposed to be invalid.", expected, actual);
	}
	
	/*
	 * isValidNomNetto test
	 */
	@Test
	public void testIsNomNettoValidPositive00() {
		double isTare = 0.05;

		boolean actual = recComValidator.isNomNettoValid(isTare);
		boolean expected = true;

		assertEquals("Failed: " + isTare + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsNomNettoValidPositive01() {
		int isTare = 1;

		boolean actual = recComValidator.isNomNettoValid(isTare);
		boolean expected = true;

		assertEquals("Failed: " + isTare + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsNomNettoValidPositive02() {
		double isTare = 20.0;

		boolean actual = recComValidator.isNomNettoValid(isTare);
		boolean expected = true;

		assertEquals("Failed: " + isTare + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsNomNettoValidNegative00() {
		double isTare = 0;

		boolean actual = recComValidator.isNomNettoValid(isTare);
		boolean expected = false;

		assertEquals("Failed: " + isTare + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsNomNettoValidNegative01() {
		double isTare = 21;

		boolean actual = recComValidator.isNomNettoValid(isTare);
		boolean expected = false;

		assertEquals("Failed: " + isTare + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsNomNettoValidNegative03() {
		double isTare = 330;

		boolean actual = recComValidator.isNomNettoValid(isTare);
		boolean expected = false;

		assertEquals("Failed: " + isTare + " is supposed to be invalid.", expected, actual);
	}
	
	/*
	 * isValidTolerance test
	 */
	@Test
	public void testIsToleranceValidPositive00() {
		double isTol = 0.1;

		boolean actual = recComValidator.isToleranceValid(isTol);
		boolean expected = true;

		assertEquals("Failed: " + isTol + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsToleranceValidPositive01() {
		int isTol = 1;

		boolean actual = recComValidator.isToleranceValid(isTol);
		boolean expected = true;

		assertEquals("Failed: " + isTol + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsToleranceValidPositive02() {
		double isTol = 10.0;

		boolean actual = recComValidator.isToleranceValid(isTol);
		boolean expected = true;

		assertEquals("Failed: " + isTol + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsToleranceValidNegative00() {
		double isTol = 0;

		boolean actual = recComValidator.isToleranceValid(isTol);
		boolean expected = false;

		assertEquals("Failed: " + isTol + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsToleranceValidNegative01() {
		double isTol = 11.0;

		boolean actual = recComValidator.isToleranceValid(isTol);
		boolean expected = false;

		assertEquals("Failed: " + isTol + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsToleranceValidNegative02() {
		double isTol = 330;

		boolean actual = recComValidator.isNomNettoValid(isTol);
		boolean expected = false;

		assertEquals("Failed: " + isTol + " is supposed to be invalid.", expected, actual);
	}
	
	/*
	 * isValidStatus test
	 */
	@Test
	public void testIsStatusValidPositive00() {
		int validStatus = 0;

		boolean actual = recComValidator.isRcStatusValid(validStatus);
		boolean expected = true;

		assertEquals("Failed: " + validStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsStatusValidPositive01() {
		int validStatus = 1;

		boolean actual = recComValidator.isRcStatusValid(validStatus);
		boolean expected = true;

		assertEquals("Failed: " + validStatus + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsStatusValidNegative00() {
		int validStatus = 2;

		boolean actual = recComValidator.isRcStatusValid(validStatus);
		boolean expected = false;

		assertEquals("Failed: " + validStatus + " is supposed to be valid.", expected, actual);
	}
}
