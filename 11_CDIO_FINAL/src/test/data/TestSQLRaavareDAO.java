package test.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLRaavareDAO;
import data.dto.RaavareDTO;

public class TestSQLRaavareDAO {

	/*
	 * Required objects.
	 */
	private SQLRaavareDAO raavareDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the RaavareDAO.
	 */
	@Before
	public void setUp() {
		raavareDAO = new SQLRaavareDAO(Connector.getInstance());
	}

	/*
	 * Resetting the RaavareDAO.
	 */
	@After
	public void tearDown() {
		raavareDAO = null;
	}

	/* 
	 * Testing SQLRaavareDAO.getRaavare(raavareId)
	 * Positive atomic test
	 */
	@Test
	public void testGetRaavarePositive() {
		try {
			int raavareId = 1;
			System.out.println("\n" + spr + " Testing SQLRaavareDAO.getRaavare(" + raavareId + ") Positive " + spr);

			System.out.println("Receiving raavareDTO with raavareId: " + raavareId);
			RaavareDTO raavareDTO = raavareDAO.getRaavare(raavareId);
			System.out.println("Received: " + raavareDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing RaavareDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareDAO.getRaavare(raavareId)
	 * Negative atomic test
	 */
	@Test
	public void testGetRaavareNegative() {
		try {
			int raavareId = 123;
			System.out.println("\n" + spr + " Testing SQLRaavareDAO.getRaavare(" + raavareId + ") Negative " + spr);

			System.out.println("Receiving raavareDTO with raavareId: " + raavareId);
			RaavareDTO raavareDTO = raavareDAO.getRaavare(raavareId);
			System.out.println("Received: " + raavareDTO);

			fail("Failed: Received non-existent RaavareDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareDAO.getRaavareList()
	 * Positive atomic test
	 */
	@Test
	public void testGetRaavareListPositive() {
		try {
			List<RaavareDTO> raavareList = raavareDAO.getRaavareList();
			System.out.println("\n" + spr + " Testing SQLRaavareDAO.getRaavareList() Positive " + spr);
			for (int i = 0; i < raavareList.size(); i++) {
				System.out.println(i + ": " + raavareList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve raavareList!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRaavareDAO.createRaavare(raavareDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreateRaavarePositive() {
		try {
			/* Make sure the RaavareDTO does not already exist. */
			int raavareId = 324;
			raavareDAO.deleteRaavare(raavareId);

			System.out.println("\n" + spr + " Testing SQLRaavareDAO.createRaavare(raavareDTO) Positive " + spr);

			RaavareDTO raavareDTO = new RaavareDTO(raavareId, "Musling", "Dong Energy");
			raavareDAO.createRaavare(raavareDTO);
			System.out.println("Created: " + raavareDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create RaavareDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRaavareDAO.createRaavare(raavareDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreateRaavareNegative() {
		try {
			int raavareId = 1;
			System.out.println("\n" + spr + " Testing SQLRaavareDAO.createRaavare(raavareDTO) Negative " + spr);

			RaavareDTO raavareDTO = new RaavareDTO(raavareId, "Meat", "Dong");
			raavareDAO.createRaavare(raavareDTO);
			System.out.println("Created: " + raavareDTO);

			fail("Failed: Created already existing RaavareDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRaavareDAO.updateRaavare(raavareDTO)
	 * Positive atomic test
	 */
	@Test
	public void testUpdateRaavarePositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLRaavareDAO.updateRaavare(raavareDTO) Positive " + spr);
			/* Creating RaavareDTO to make sure that it exists. */
			int raavareId = 423;
			raavareDAO.deleteRaavare(423);
			RaavareDTO raavareDTO = new RaavareDTO(raavareId, "Fish", "Fish Sticks");
			raavareDAO.createRaavare(raavareDTO);

			String newName = "Salt Fish";
			String newSupplier = "US of A";

			RaavareDTO updatedRaavareDTO = new RaavareDTO(raavareId, newName, newSupplier);

			System.out.println("Created: " + raavareDAO.getRaavare(raavareId));
			raavareDAO.updateRaavare(updatedRaavareDTO);
			System.out.println("Updated: " + raavareDAO.getRaavare(raavareId));

			String expected = newName;
			String actual = raavareDAO.getRaavare(raavareId).getraavareName();

			assertEquals("Failed: The updated name does not match!", expected, actual);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing RaavareDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRaavareDAO.deleteRaavare(raavareId)
	 * Positive atomic test
	 */
	@Test
	public void testDeleteRaavarePositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLRaavareDAO.deleteRaavare(raavareId) Positive " + spr);
			/* Creating RaavareDTO to make sure that it exists. */
			int raavareId = 334;
			raavareDAO.deleteRaavare(raavareId);
			RaavareDTO raavareDTO = new RaavareDTO(raavareId, "Veron", "Weat & Co.");
			raavareDAO.createRaavare(raavareDTO);

			System.out.println("Created: " + raavareDTO);
			raavareDAO.deleteRaavare(raavareId);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing RaavareDTO!");
			System.out.println(lspr);
		}
	}

}