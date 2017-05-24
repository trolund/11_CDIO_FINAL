package test.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLRaavareBatchDAO;
import data.dto.RaavareBatchDTO;

public class TestSQLRaavareBatchDAO {

	/*
	 * Required objects.
	 */
	private SQLRaavareBatchDAO rbDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the RaavareBatchDAO.
	 */
	@Before
	public void setUp() {
		rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());
	}

	/*
	 * Resetting the RaavareBatchDAO.
	 */
	@After
	public void tearDown() {
		rbDAO = null;
	}

	/* 
	 * Testing SQLRaavareBatchDAO.getRaavareBatch(rbId)
	 * Positive atomic test
	 */
	@Test
	public void testGetRaavareBatchPositive() {
		try {
			int rbId = 1;
			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.getRaavareBatch(" + rbId + ") Positive " + spr);

			System.out.println("Receiving RaavareBatchDTO with rbId: " + rbId + ".");
			RaavareBatchDTO rbDTO = rbDAO.getRaavareBatch(rbId);
			System.out.println("Received: " + rbDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing RaavareBatchDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareBatchDAO.getRaavareBatch(rbId)
	 * Negative atomic test
	 */
	@Test
	public void testGetRaavareBatchNegative() {
		try {
			int rbId = 102;

			/* Make sure that the ProductBatchDTO does not exist. */
			rbDAO.deleteRaavareBatch(rbId);

			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.getRaavareBatch(" + rbId + ") Negative " + spr);

			System.out.println("Receiving RaavareBatchDTO with rbId: " + rbId + ".");
			RaavareBatchDTO rbDTO = rbDAO.getRaavareBatch(rbId);
			System.out.println("Received: " + rbDTO);

			fail("Failed: Received non-existent RaavareBatchDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareBatchDAO.getRaavareBatchList()
	 * Positive atomic test
	 */
	@Test
	public void testGetRaavareBatchListPositive() {
		try {
			List<RaavareBatchDTO> rbList = rbDAO.getRaavareBatchList();
			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.getRaavareBatchList() Positive " + spr);
			for (int i = 0; i < rbList.size(); i++) {
				System.out.println(i + ": " + rbList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve SQLRaavareBatchDAO.getRaavareBatchList()!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareBatchDAO.getRaavareBatchList(raavareId)
	 * Positive atomic test
	 */
	@Test
	public void testGetRaavareBatchListParameterPositive() {
		try {
			int raavareId = 1;
			List<RaavareBatchDTO> rbList = rbDAO.getRaavareBatchList(raavareId);
			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.getRaavareBatchList(" + raavareId + ") Positive " + spr);
			for (int i = 0; i < rbList.size(); i++) {
				System.out.println(i + ": " + rbList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve RaavareBatchList!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRaavareBatchDAO.createRaavareBatch(rbDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreateRaavareBatchPositive() {
		try {
			/* Make sure the RaavareBatchDTO does not already exist. */
			int rbId = 234;
			rbDAO.deleteRaavareBatch(rbId);

			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.createRaavareBatch(rbDTO) Positive " + spr);

			RaavareBatchDTO rbDTO = new RaavareBatchDTO(rbId, 1, 42);
			rbDAO.createRaavareBatch(rbDTO);
			System.out.println("Created: " + rbDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create RaavareBatchDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRaavareBatchDAO.createRaavareBatch(rbDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreateRaavareBatchNegative() {
		try {
			int rbId = 1;
			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.createRaavareBatch(rbDTO) Negative " + spr);

			RaavareBatchDTO rbDTO = new RaavareBatchDTO(rbId, 1, 42);
			rbDAO.createRaavareBatch(rbDTO);
			System.out.println("Created: " + rbDTO);

			fail("Failed: Created already existing RaavareBatchDTO!!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareBatchDAO.updateRaavareBatch(rbDTO)
	 * Positive atomic test
	 */
	@Test
	public void testUpdateRaavareBatchPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.updateRaavareBatch(rbDTO) Positive " + spr);
			/* Creating RaavareBatchDTO to make sure that it exists. */
			int rbId = 423;
			rbDAO.deleteRaavareBatch(rbId);
			RaavareBatchDTO rbDTO = new RaavareBatchDTO(rbId, 1, 5435);
			rbDAO.createRaavareBatch(rbDTO);

			int newAmount = 9999;

			RaavareBatchDTO updatedRbDTO = new RaavareBatchDTO(rbId, 1, newAmount);

			System.out.println("Created: " + rbDAO.getRaavareBatch(rbId));
			rbDAO.updateRaavareBatch(updatedRbDTO);
			System.out.println("Updated: " + rbDAO.getRaavareBatch(rbId));

			double expected = newAmount;
			double actual = rbDAO.getRaavareBatch(rbId).getAmount();

			assertEquals("Failed: The updated amount does not match!", expected, actual, 0);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing RaavareBatchDTO");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRaavareBatchDAO.deleteRaavareBatch(rbId)
	 * Positive atomic test
	 */
	@Test
	public void testDeleteRaavareBatchPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLRaavareBatchDAO.deleteRaavareBatch(rbId) Positive " + spr);
			/* Creating RaavareBatchDTO to make sure that it exists. */
			int rbId = 4552;
			rbDAO.deleteRaavareBatch(rbId);
			RaavareBatchDTO rbDTO = new RaavareBatchDTO(rbId, 1, 666669);
			rbDAO.createRaavareBatch(rbDTO);

			System.out.println("Created: " + rbDTO);
			rbDAO.deleteRaavareBatch(rbId);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing RaavareBatchDTO!");
			System.out.println(lspr);
		}
	}

}