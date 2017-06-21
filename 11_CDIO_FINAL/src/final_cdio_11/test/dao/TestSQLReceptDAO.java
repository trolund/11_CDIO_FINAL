package final_cdio_11.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dto.ReceptDTO;

public class TestSQLReceptDAO {

	/*
	 * Required objects.
	 */
	private IReceptDAO receptDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the ReceptDAO.
	 */
	@Before
	public void setUp() {
		receptDAO = new SQLReceptDAO(Connector.getInstance());
	}

	/*
	 * Resetting the ReceptDAO.
	 */
	@After
	public void tearDown() {
		receptDAO = null;
	}

	/*
	 * Testing SQLReceptDAO.getRecept(receptId) Positive atomic test
	 */
	@Test
	public void testGetReceptPositive() {
		try {
			int receptId = 1;
			System.out.println("\n" + spr + " Testing SQLReceptDAO.getRecept(" + receptId + ") Positive " + spr);

			System.out.println("Receiving receptDTO with receptId: " + receptId);
			ReceptDTO receptDTO = receptDAO.getRecept(receptId);
			System.out.println("Received: " + receptDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing ReceptDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptDAO.getRecept(receptId) Negative atomic test
	 */
	@Test
	public void testGetReceptNegative() {
		try {
			int receptId = 42342;
			System.out.println("\n" + spr + " Testing SQLReceptDAO.getRecept(" + receptId + ") Negative " + spr);

			System.out.println("Receiving receptDTO with receptId: " + receptId);
			ReceptDTO receptDTO = receptDAO.getRecept(receptId);
			System.out.println("Received: " + receptDTO);

			fail("Failed: Received non-existent ReceptDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptDAO.getReceptList() Positive atomic test
	 */
	@Test
	public void testGetReceptListPositive() {
		try {
			List<ReceptDTO> receptList = receptDAO.getReceptList();

			System.out.println("\n" + spr + " Testing SQLReceptDAO.getReceptList() Positive " + spr);

			if (receptList == null) fail("Failed: Failed to retrieve receptList!");

			for (int i = 0; i < receptList.size(); i++) {
				System.out.println(i + ": " + receptList.get(i));
			}

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve receptList!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptDAO.createRecept(receptDTO) Negative atomic test
	 */
	@Test
	public void testCreateReceptNegative() {
		try {
			int receptId = 1;
			System.out.println("\n" + spr + " Testing SQLReceptDAO.createRecept(receptDTO) Negative " + spr);

			ReceptDTO receptDTO = new ReceptDTO(receptId, "Mixed", 0);
			receptDAO.createRecept(receptDTO);
			System.out.println("Created: " + receptDTO);

			fail("Failed: Created already existing ReceptDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptDAO.updateRecept(receptDTO) Positive atomic test
	 */
	@Test
	public void testUpdateReceptPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLReceptDAO.updateRecept(receptDTO) Positive " + spr);
			int receptId = 1;

			String newName = "Unmixed Fish";

			ReceptDTO updatedReceptDTO = new ReceptDTO(receptId, newName, 0);

			System.out.println("Created: " + receptDAO.getRecept(receptId));
			receptDAO.updateRecept(updatedReceptDTO);
			System.out.println("Updated: " + receptDAO.getRecept(receptId));

			String expected = newName;
			String actual = receptDAO.getRecept(receptId).getReceptName();

			assertEquals("Failed: The updated name does not match!", expected, actual);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing ReceptDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptDAO.deleteRecept(receptId) Positive atomic test
	 */
	@Test
	public void testDeleteReceptPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLReceptDAO.deleteRecept(receptId) Positive " + spr);
			int receptId = 3;

			System.out.println("Created: " + receptDAO.getRecept(receptId));
			receptDAO.deleteRecept(receptId);
			System.out.println("Deleted.");

			if (receptDAO.getRecept(receptId).getStatus() != 1) fail("Failed: Failed to delete existing ReceptDTO!");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing ReceptDTO!");
			System.out.println(lspr);
		}
	}

}