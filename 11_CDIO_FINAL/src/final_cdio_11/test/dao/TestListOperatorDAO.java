package final_cdio_11.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.ListOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;

public class TestListOperatorDAO {

	/*
	 * Required objects.
	 */
	private ListOperatorDAO oprDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the operator DAO.
	 */
	@Before
	public void setUp() {
		oprDAO = new ListOperatorDAO();
	}

	/*
	 * Resetting the operator DAO.
	 */
	@After
	public void tearDown() {
		oprDAO = null;
	}

	/*
	 * Testing ListOperatorDAO.getOperator(oprId) Positive atomic test
	 */
	@Test
	public void testGetOperatorPositive() {
		OperatorDTO dto = null;
		int oprId = 101;
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.getOperator(" + oprId + ") Positive " + spr);
			dto = oprDAO.getOperator(oprId);
			System.out.println(dto);
			System.out.println(lspr);
		} catch (DALException e) {
			e.printStackTrace();
		} finally {
			int actual = dto.getOprId();
			int expected = oprId;
			assertEquals("Failed: Got wrong oprId number.", expected, actual);
		}
	}

	/*
	 * Testing ListOperatorDAO.getOperator(oprId) Negative atomic test
	 */
	@Test
	public void testGetOperatorNegative() {
		int oprId = 2342;
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.getOperator(" + oprId + ") Negative " + spr);
			OperatorDTO dto = oprDAO.getOperator(oprId);
			int expected = oprId;
			int actual = dto.getOprId();
			assertEquals("Failed: Received non-existing operator!", expected, actual);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing ListOperatorDAO.getOperatorList() Positive atomic test
	 */
	@Test
	public void testGetOperatorListPositive() {
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.getOperatorList() Positive " + spr);
			for (OperatorDTO dto : oprDAO.getOperatorList()) {
				System.out.println(dto);
				if (dto == null) {
					fail("Failed: Null object returned!");
				}
			}
			System.out.println(lspr);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Testing ListOperatorDAO.createOperator(oprDTO) Positive atomic test
	 */
	@Test
	public void testCreateOperatorPositive() {
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.createOperator() Positive " + spr);
			int oprId = 49;
			boolean expected = true;
			boolean actual = false;
			oprDAO.createOperator(new OperatorDTO(oprId, "Franco", "Francis", "FRC", "frc@fr.fr", "6666-666-69", "666", 0));
			for (OperatorDTO dto : oprDAO.getOperatorList()) {
				if (dto.getOprId() == oprId) {
					actual = true;
					System.out.println("Found operator with id: " + oprId);
				}
			}
			assertEquals("Failed: Created Operator not found!", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Testing ListOperatorDAO.createOperator(oprDTO) Negative atomic test
	 */
	@Test
	public void testCreateOperatorNegative() {
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.createOperator() Negative " + spr);
			int oprId = 101;
			oprDAO.createOperator(new OperatorDTO(oprId, "Franco", "Franciz", "FRC", "fr@fr.fr", "6666-666-69", "666", 0));
			fail("Failed: Managed to create existing operator!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing ListOperatorDAO.updateOperator(oprDTO) Positive atomic test
	 */
	@Test
	public void testUpdateOperatorPositive() {
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.updateOperator(oprDTO) Positive " + spr);
			int oprId = 99;
			oprDAO.createOperator(new OperatorDTO(oprId, "Jim", "Jimson", "JM", "jim@jm.jk", "999999-1122", "password", 0));
			System.out.println("Created: " + oprDAO.getOperator(oprId));

			oprDAO.updateOperator(new OperatorDTO(99, "Jim", "Jimmie", "JM123", "jim@jm.jm", "111111-4444", "nytpassword", 0));
			System.out.println("Updated: " + oprDAO.getOperator(oprId));

			String expected = "JM123";
			String actual = oprDAO.getOperator(99).getOprIni();

			assertEquals("Failed: Did not update the DTO correctly.", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Testing ListOperatorDAO.updateOperator(oprDTO) Negative atomic test
	 */
	@Test
	public void testUpdateOperatorNegative() {
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.updateOperator(oprDTO) Negative " + spr);
			int oprId = 99;

			oprDAO.updateOperator(new OperatorDTO(99, "Jim", "Jimmie", "JM", "jk@jk.jk", "111111-4444", "nytpassword", 0));
			System.out.println("Updated: " + oprDAO.getOperator(oprId));
			fail("Failed: Updated non-existent operator!");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing ListOperatorDAO.deleteOperator(oprId) Positive atomic test
	 */
	@Test
	public void testDeleteOperatorPositive() {
		try {
			System.out.println("\n" + spr + " Testing ListOperatorDAO.deleteOperator(oprDTO) Positive " + spr);
			int oprId = 54;

			oprDAO.createOperator(new OperatorDTO(oprId, "John", "Johnson", "JN", "john@sen.jk", "999999-1234", "pumpkin", 0));
			System.out.println("Created: " + oprDAO.getOperator(oprId));

			oprDAO.deleteOperator(oprId);
			System.out.println("Deleted!");

			oprDAO.getOperator(oprId);

			fail("Failed: Operator still exists after deletion!");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

}