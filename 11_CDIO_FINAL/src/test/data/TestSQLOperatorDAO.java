package test.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLOperatorDAO;
import data.dto.OperatorDTO;

public class TestSQLOperatorDAO {

	/*
	 * Required objects.
	 */
	private SQLOperatorDAO oprDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the operator DAO.
	 */
	@Before
	public void setUp() {
		oprDAO = new SQLOperatorDAO(Connector.getInstance());
	}

	/*
	 * Resetting the operator DAO.
	 */
	@After
	public void tearDown() {
		oprDAO = null;
	}

	/* 
	 * Testing SQLOperatorDAO.getOperator(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testGetOperatorPositive() {
		try {
			int oprId = 1;
			System.out.println("\n" + spr + " Testing SQLOperatorDAO.getOperator(" + oprId + ") Positive " + spr);

			System.out.println("Receiving OperatorDTO with oprId: " + oprId);
			OperatorDTO oprDTO = oprDAO.getOperator(oprId);
			System.out.println("Received: " + oprDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve existing OperatorDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLOperatorDAO.getOperator(oprId)
	 * Negative atomic test
	 */
	@Test
	public void testGetOperatorNegative() {
		try {
			int oprId = 3433;
			/* Make sure the OperatorDTO does not exist. */
			oprDAO.deleteOperator(oprId);

			System.out.println("\n" + spr + " Testing SQLOperatorDAO.getOperator(" + oprId + ") Negative " + spr);

			System.out.println("Receiving OperatorDTO with oprId: " + oprId);
			OperatorDTO oprDTO = oprDAO.getOperator(oprId);
			System.out.println("Received: " + oprDTO);

			fail("Failed: Received non-existing OperatorDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLOperatorDAO.getOperatorList()
	 * Positive atomic test
	 */
	@Test
	public void testGetOperatorListPositive() {
		try {
			List<OperatorDTO> oprList = oprDAO.getOperatorList();
			System.out.println("\n" + spr + " Testing SQLOperatorDAO.getOperatorList() Positive " + spr);
			for (int i = 0; i < oprList.size(); i++) {
				System.out.println(i + ": " + oprList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve OperatorList!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLOperatorDAO.createOperator(oprDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreateOperatorPositive() {
		try {
			/* Make sure the OperatorDTO does not already exist. */
			int oprId = 434;
			oprDAO.deleteOperator(oprId);

			System.out.println("\n" + spr + " Testing SQLOperatorDAO.createOperator(oprDTO) Positive " + spr);

			OperatorDTO oprDTO = new OperatorDTO(oprId, "Jezzie", "JZE", "32342-2343", "badpassword");
			oprDAO.createOperator(oprDTO);
			System.out.println("Created: " + oprDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create OperatorDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLOperatorDAO.createOperator(oprDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreateOperatorNegative() {
		try {
			int oprId = 1;
			System.out.println("\n" + spr + " Testing SQLOperatorDAO.createOperator(oprDTO) Negative " + spr);

			System.out.println("Attempting to create OperatorDTO with oprId: " + oprId);
			OperatorDTO oprDTO = new OperatorDTO(oprId, "Jezzie", "JZE", "32342-2343", "badpassword");
			oprDAO.createOperator(oprDTO);
			System.out.println("Created: " + oprDTO);

			fail("Failed: Created already existing OperatorDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLOperatorDAO.updateOperator(oprDTO)
	 * Positive atomic test
	 */
	@Test
	public void testUpdateOperatorPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorDAO.updateOperator(oprDTO) Positive " + spr);
			/* Creating OperatorDTO to make sure that it exists. */
			int oprId = 842;
			oprDAO.deleteOperator(oprId);
			OperatorDTO oprDTO = new OperatorDTO(oprId, "Jason", "JSN", "23234", "jason4lyfe");
			oprDAO.createOperator(oprDTO);

			String newName = "Dannuke";
			String newCpr = "9999";

			OperatorDTO updatedOprDTO = new OperatorDTO(oprId, newName, oprDTO.getOprIni(), newCpr, oprDTO.getOprPassword());

			System.out.println("Created: " + oprDAO.getOperator(oprId));
			oprDAO.updateOperator(updatedOprDTO);
			System.out.println("Updated: " + oprDAO.getOperator(oprId));

			String expected = newName;
			String actual = oprDAO.getOperator(oprId).getOprName();

			assertEquals("Failed: The updated name does not match!", expected, actual);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing OperatorDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLOperatorDAO.deleteOperator(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testDeleteOperatorPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorDAO.deleteOperator(oprId) Positive " + spr);
			/* Creating OperatorDTO to make sure that it exists. */
			int oprId = 3234;
			oprDAO.deleteOperator(oprId);
			OperatorDTO oprDTO = new OperatorDTO(oprId, "Drake", "DKE", "94432", "thuglyfe");
			oprDAO.createOperator(oprDTO);

			System.out.println("Created: " + oprDTO);
			oprDAO.deleteOperator(oprId);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing OperatorDTO!");
			System.out.println(lspr);
		}
	}

}