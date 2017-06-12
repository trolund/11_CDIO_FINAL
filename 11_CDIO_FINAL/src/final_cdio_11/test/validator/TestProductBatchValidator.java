package final_cdio_11.test.validator;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.data.validator.IProductBatchValidator;
import final_cdio_11.java.data.validator.ProductBatchValidator;

public class TestProductBatchValidator {

	private IProductBatchValidator pbValidator;

	@Before
	public void setUp() throws Exception {
		pbValidator = new ProductBatchValidator();
	}

	@After
	public void tearDown() throws Exception {
		pbValidator = null;
	}

	/*
	 * isPbIdValid test.
	 */
	@Test
	public void testIsproBatIdValidPositive00() {
		int proBatId = 1;

		boolean actual = pbValidator.isPbIdValid(proBatId);
		boolean expected = true;

		assertEquals("Failed: " + proBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidPositive01() {
		int proBatId = 99999999;

		boolean actual = pbValidator.isPbIdValid(proBatId);
		boolean expected = true;

		assertEquals("Failed: " + proBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidPositive02() {
		int proBatId = 123;

		boolean actual = pbValidator.isPbIdValid(proBatId);
		boolean expected = true;

		assertEquals("Failed: " + proBatId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidNegative00() {
		int proBatId = 0;

		boolean actual = pbValidator.isPbIdValid(proBatId);
		boolean expected = false;

		assertEquals("Failed: " + proBatId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidNegative01() {
		int proBatId = 942034029;

		boolean actual = pbValidator.isPbIdValid(proBatId);
		boolean expected = false;

		assertEquals("Failed: " + proBatId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsproBatIdValidNegative02() {
		int proBatId = -1;

		boolean actual = pbValidator.isPbIdValid(proBatId);
		boolean expected = false;

		assertEquals("Failed: " + proBatId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * itemStatusValid test.
	 */
	@Test
	public void testIsPbcStatusValidPositive00() {
		int itemStatus = 0;

		boolean actual = pbValidator.isItemStatusValid(itemStatus);
		boolean expected = true;

		assertEquals("Failed: " + itemStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidPositive01() {
		int itemStatus = 1;

		boolean actual = pbValidator.isItemStatusValid(itemStatus);
		boolean expected = true;

		assertEquals("Failed: " + itemStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidPositive03() {
		int itemStatus = 2;

		boolean actual = pbValidator.isItemStatusValid(itemStatus);
		boolean expected = true;

		assertEquals("Failed: " + itemStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidNegative00() {
		int itemStatus = -1;

		boolean actual = pbValidator.isItemStatusValid(itemStatus);
		boolean expected = false;

		assertEquals("Failed: " + itemStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidNegative01() {
		int itemStatus = 3;

		boolean actual = pbValidator.isItemStatusValid(itemStatus);
		boolean expected = false;

		assertEquals("Failed: " + itemStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbcStatusValidNegative03() {
		int itemStatus = 100;

		boolean actual = pbValidator.isItemStatusValid(itemStatus);
		boolean expected = false;

		assertEquals("Failed: " + itemStatus + " is supposed to be valid.", expected, actual);
	}

	/*
	 * isReceptIdValid test.
	 */
	@Test
	public void testIsReceptIdValidPositive00() {
		int recId = 1;

		boolean actual = pbValidator.isPbIdValid(recId);
		boolean expected = true;

		assertEquals("Failed: " + recId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidPositive01() {
		int recId = 99999999;

		boolean actual = pbValidator.isPbIdValid(recId);
		boolean expected = true;

		assertEquals("Failed: " + recId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidPositive02() {
		int recId = 123;

		boolean actual = pbValidator.isPbIdValid(recId);
		boolean expected = true;

		assertEquals("Failed: " + recId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidNegative00() {
		int recId = 0;

		boolean actual = pbValidator.isPbIdValid(recId);
		boolean expected = false;

		assertEquals("Failed: " + recId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidNegative01() {
		int recId = 942034029;

		boolean actual = pbValidator.isPbIdValid(recId);
		boolean expected = false;

		assertEquals("Failed: " + recId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsReceptIdValidNegative02() {
		int recId = -1;

		boolean actual = pbValidator.isPbIdValid(recId);
		boolean expected = false;

		assertEquals("Failed: " + recId + " is supposed to be invalid.", expected, actual);
	}

	/*
	 * isPbStatusValid test
	 */
	@Test
	public void testIsPbStatusValidPositive00() {
		int pbStatus = 0;

		boolean actual = pbValidator.isPbStatusValid(pbStatus);
		boolean expected = true;

		assertEquals("Failed: " + pbStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbStatusValidPositive01() {
		int pbStatus = 1;

		boolean actual = pbValidator.isPbStatusValid(pbStatus);
		boolean expected = true;

		assertEquals("Failed: " + pbStatus + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsPbStatusValidFalse00() {
		int pbStatus = 3;

		boolean actual = pbValidator.isPbStatusValid(pbStatus);
		boolean expected = false;

		assertEquals("Failed: " + pbStatus + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsPbValidPositive() {
		ProductBatchDTO pbDTO = new ProductBatchDTO(1, 1, 2, 0);

		boolean actual = pbValidator.isPbValid(pbDTO);
		boolean expected = true;

		assertEquals("Failed: " + pbDTO + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsPbValidNegativeStatus() {
		ProductBatchDTO pbDTO = new ProductBatchDTO(1, 1, 2, 4);

		boolean actual = pbValidator.isPbValid(pbDTO);
		boolean expected = false;

		assertEquals("Failed: " + pbDTO + " is supposed to be invalid.", expected, actual);
	}
	
	@Test
	public void testIsPbValidNegativeId() {
		ProductBatchDTO pbDTO = new ProductBatchDTO(0, 1, 2, 4);

		boolean actual = pbValidator.isPbValid(pbDTO);
		boolean expected = false;

		assertEquals("Failed: " + pbDTO + " is supposed to be invalid.", expected, actual);
	}
	

}