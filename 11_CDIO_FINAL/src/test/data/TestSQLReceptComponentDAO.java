package test.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLReceptComponentDAO;
import data.dto.ReceptComponentDTO;

public class TestSQLReceptComponentDAO {

	/*
	 * Required objects.
	 */
	private SQLReceptComponentDAO rcDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the ReceptComponentDAO.
	 */
	@Before
	public void setUp() {
		rcDAO = new SQLReceptComponentDAO(Connector.getInstance());
	}

	/*
	 * Resetting the ReceptComponentDAO.
	 */
	@After
	public void tearDown() {
		rcDAO = null;
	}

	/* 
	 * Testing SQLReceptComponentDAO.getRC(receptId, raavareId)
	 * Positive atomic test
	 */
	@Test
	public void testGetRCPositive() {
		try {
			int receptId = 1;
			int raavareId = 2;
			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.getRC(" + receptId + ", " + raavareId + ") Positive " + spr);

			System.out.println("Receiving ReceptComponentDTO with receptId: " + receptId + ", raavareId: " + raavareId + ".");
			ReceptComponentDTO rcDTO = rcDAO.getReceptComponent(receptId, raavareId);
			System.out.println("Received: " + rcDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing ReceptComponentDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLReceptComponentDAO.getRC(receptId, raavareId)
	 * Negative atomic test
	 */
	@Test
	public void testGetRCNegative() {
		try {
			int receptId = 13;
			int raavareId = 42;
			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.getRC(" + receptId + ", " + raavareId + ") Negative " + spr);

			System.out.println("Receiving ReceptComponentDTO with receptId: " + receptId + ", raavareId: " + raavareId + ".");
			ReceptComponentDTO rcDTO = rcDAO.getReceptComponent(receptId, raavareId);
			System.out.println("Received: " + rcDTO);

			fail("Failed: Received non-existent ReceptComponentDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLReceptComponentDAO.getRCList()
	 * Positive atomic test
	 */
	@Test
	public void testGetRCListPositive() {
		try {
			List<ReceptComponentDTO> rcList = rcDAO.getReceptComponentList();
			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.getRCList() Positive " + spr);
			for (int i = 0; i < rcList.size(); i++) {
				System.out.println(i + ": " + rcList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve ReceptComponentList!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLReceptComponentDAO.getRCList(receptId)
	 * Positive atomic test
	 */
	@Test
	public void testGetRCListParameterPositive() {
		try {
			int receptId = 1;
			List<ReceptComponentDTO> rcList = rcDAO.getReceptComponentList(receptId);
			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.getRCList(" + receptId + ") Positive " + spr);
			for (int i = 0; i < rcList.size(); i++) {
				System.out.println(i + ": " + rcList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve ReceptComponentList with receptId!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptComponentDAO.createRC(rcDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreateRCPositive() {
		try {
			/* Make sure the ReceptComponentDTO does not already exist. */
			int receptId = 1;
			int raavareId = 2;
			rcDAO.deleteReceptComponent(receptId, raavareId);

			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.createRC(rcDTO) Positive " + spr);

			ReceptComponentDTO rcDTO = new ReceptComponentDTO(receptId, raavareId, 23.23, 44.44);
			rcDAO.createReceptComponent(rcDTO);
			System.out.println("Created: " + rcDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create rcDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptComponentDAO.createRC(rcDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreateRCNegative() {
		try {
			int receptId = 2;
			int raavareId = 1;

			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.createRC(rcDTO) Negative " + spr);

			ReceptComponentDTO rcDTO = new ReceptComponentDTO(receptId, raavareId, 23.23, 44.44);
			rcDAO.createReceptComponent(rcDTO);
			System.out.println("Created: " + rcDTO);

			fail("Failed: Created already existing ReceptComponentDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLReceptComponentDAO.updateRC(rcDTO)
	 * Positive atomic test
	 */
	@Test
	public void testUpdateRCPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLReceptComponentDAO.updateRC(rcDTO) Positive " + spr);
			/* Creating ReceptComponentDTO to make sure that it exists. */
			int receptId = 2;
			int raavareId = 3;
			rcDAO.deleteReceptComponent(receptId, raavareId);
			ReceptComponentDTO rcDTO = new ReceptComponentDTO(receptId, raavareId, 50.00, 66.44);
			rcDAO.createReceptComponent(rcDTO);

			double newNomNetto = 234.43;
			double newTolerance = 32.325;

			ReceptComponentDTO UpdatedRcDTO = new ReceptComponentDTO(receptId, raavareId, newNomNetto, newTolerance);

			System.out.println("Created: " + rcDAO.getReceptComponent(receptId, raavareId));
			rcDAO.updateReceptComponent(UpdatedRcDTO);
			System.out.println("Updated: " + rcDAO.getReceptComponent(receptId, raavareId));

			double expected = newNomNetto;
			double actual = rcDAO.getReceptComponent(receptId, raavareId).getNomNetto();

			assertEquals("Failed: The updated nomNetto does not match!", expected, actual, 0);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing ReceptComponentDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLReceptComponentDAO.deleteRC(receptId, raavareId)
	 * Positive atomic test
	 */
	@Test
	public void testDeletePBCPositive() {
		try {
			System.out.println("\n" + spr + "Testing SQLReceptComponentDAO.deleteRC(receptId, raavareId) Positive " + spr);
			/* Creating ReceptComponentDTO to make sure that it exists. */
			int receptId = 2;
			int raavareId = 2;
			rcDAO.deleteReceptComponent(receptId, raavareId);
			ReceptComponentDTO rcDTO = new ReceptComponentDTO(receptId, raavareId, 50.00, 66.44);
			rcDAO.createReceptComponent(rcDTO);

			System.out.println("Created: " + rcDTO);
			rcDAO.deleteReceptComponent(receptId, raavareId);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing ReceptComponentDTO!");
			System.out.println(lspr);
		}
	}

}