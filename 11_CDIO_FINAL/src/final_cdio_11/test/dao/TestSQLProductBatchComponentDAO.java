package final_cdio_11.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLProductBatchComponentDAO;
import final_cdio_11.java.data.dto.ProductBatchComponentDTO;

public class TestSQLProductBatchComponentDAO {

	/*
	 * Required objects.
	 */
	private SQLProductBatchComponentDAO pbcDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the PBC DAO.
	 */
	@Before
	public void setUp() {
		pbcDAO = new SQLProductBatchComponentDAO(Connector.getInstance());
	}

	/*
	 * Resetting the PBC DAO.
	 */
	@After
	public void tearDown() {
		pbcDAO = null;
	}

	/*
	 * Testing SQLProductBatchComponentDAO.getPBC(pbId, rbId) Positive atomic test
	 */
	@Test
	public void testGetPBCPositive() {
		try {
			int pbId = 4;
			int rbId = 6;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			/* Create it */
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 23.4, 345.3, 10, 0);
			pbcDAO.createProductBatchComponent(pbcDTO);

			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBC(" + pbId + ", " + rbId + ") Positive " + spr);

			System.out.println("Receiving ProductBatchComponentDTO with pbId: " + pbId + ", rbId: " + rbId + ".");
			ProductBatchComponentDTO newPbcDTO = pbcDAO.getProductBatchComponent(pbId, rbId);
			System.out.println("Received: " + newPbcDTO);

			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing ProductBatchComponentDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.getPBC(pbId, rbId) Negative atomic test
	 */
	@Test
	public void testGetPBCNegative() {
		try {
			int pbId = 54;
			int rbId = 43;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBC(" + pbId + ", " + rbId + ") Negative " + spr);

			System.out.println("Receiving ProductBatchComponentDTO with pbId: " + pbId + ", rbId: " + rbId + ".");
			ProductBatchComponentDTO pbcDTO = pbcDAO.getProductBatchComponent(pbId, rbId);
			System.out.println("Received: " + pbcDTO);

			fail("Failed: Received non-existent ProductBatchComponentDTO!");
			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.getPBCList() Positive atomic test
	 */
	@Test
	public void testGetPBCListPositive() {
		try {
			List<ProductBatchComponentDTO> pbcList = pbcDAO.getProductBatchComponentList();
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBCList() Positive " + spr);

			if (pbcList == null) fail("Failed: Failed to retrieve ProductBatchComponentList!");

			for (int i = 0; i < pbcList.size(); i++) {
				System.out.println(i + ": " + pbcList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve ProductBatchComponentList!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.getPBCList(rbId); Positive atomic test
	 */
	@Test
	public void testGetPBCListParameterPositive() {
		try {
			int rbId = 2;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			pbcDAO.createProductBatchComponent(new ProductBatchComponentDTO(2, rbId, 23.4, 345.3, 10, 0));
			pbcDAO.createProductBatchComponent(new ProductBatchComponentDTO(1, rbId, 23.4, 345.3, 10, 0));

			List<ProductBatchComponentDTO> pbcList = pbcDAO.getProductBatchComponentList(rbId);
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBCList(" + rbId + ") Positive " + spr);

			if (pbcList == null) fail("Failed: Failed to retrieve ProductBatchComponentList!");

			for (int i = 0; i < pbcList.size(); i++) {
				System.out.println(i + ": " + pbcList.get(i));
			}

			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();
			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve SQLProductBatchComponentDAO.getPBCList(rbId)!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.createPBC(pbcDTO) Positive atomic test
	 */
	@Test
	public void testCreatePBCPositive() {
		try {
			int pbId = 1;
			int rbId = 2;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.createPBC(pbcDTO) Positive " + spr);

			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 502.23, 43.23, 1, 0);
			pbcDAO.createProductBatchComponent(pbcDTO);
			System.out.println("Created: " + pbcDTO);

			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();
			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create pbcDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.createPBC(pbcDTO) Negative atomic test
	 */
	@Test
	public void testCreatePBCNegative() {
		try {
			int pbId = 1;
			int rbId = 2;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			/* Create it. */
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 432.23, 23.23, 1, 0);
			pbcDAO.createProductBatchComponent(pbcDTO);

			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.createPBC(pbcDTO) Negative " + spr);
			pbcDAO.createProductBatchComponent(pbcDTO);
			System.out.println("Created: " + pbcDTO);

			fail("Failed: Created already existing ProductBatchComponentDTO!");
			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.updatePBC(pbcDTO) Positive atomic test
	 */
	@Test
	public void testUpdatePBCPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.updatePBC(pbcDTO) Positive " + spr);

			int pbId = 2;
			int rbId = 3;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			/* Creating pbcDTO to make sure that it exists. */
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 99.99, 11.22, 1, 0);
			pbcDAO.createProductBatchComponent(pbcDTO);

			double newTara = 23423.234;
			double newNetto = 555.342;

			ProductBatchComponentDTO updatedPbcDTO = new ProductBatchComponentDTO(pbId, rbId, newTara, newNetto, 1, 0);

			System.out.println("Created: " + pbcDAO.getProductBatchComponent(pbId, rbId));
			pbcDAO.updateProductBatchComponent(updatedPbcDTO);
			System.out.println("Updated: " + pbcDAO.getProductBatchComponent(pbId, rbId));

			double expected = newTara;
			double actual = pbcDAO.getProductBatchComponent(pbId, rbId).getTara();

			assertEquals("Failed: The updated name does not match!", expected, actual, 0);

			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();
			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing ProductBatchComponentDTO");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.deletePBC(pbId, rbId) Positive atomic test
	 */
	@Test
	public void testDeletePBCPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.deletePBC(pbId, rbId) Positive " + spr);

			int pbId = 2;
			int rbId = 3;

			/* Make sure that the productbatchcomponent doesn't already exist. */
			String deleteSql = "DELETE FROM produktbatchkomponent WHERE pb_id = " + pbId + " AND rb_id = " + rbId;
			Connector.getInstance().getConnection().prepareStatement(deleteSql).executeUpdate();

			/* Creating ProductBatchComponentDTO to make sure that it exists. */
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 22.22, 33.33, 1, 0);
			pbcDAO.createProductBatchComponent(pbcDTO);

			System.out.println("Created: " + pbcDTO);
			pbcDAO.deleteProductBatchComponent(pbId, rbId);
			System.out.println("Deleted.");

			if (pbcDAO.getProductBatchComponent(pbId, rbId).getStatus() != 1) fail("Failed: Failed to delete existing ProductBatchComponentDTO!");

			System.out.println(lspr);
		} catch (DALException | SQLException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing ProductBatchComponentDTO!");
			System.out.println(lspr);
		}
	}

}