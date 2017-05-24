package test.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLProductBatchComponentDAO;
import data.dto.ProductBatchComponentDTO;

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
	 * Testing SQLProductBatchComponentDAO.getPBC(pbId, rbId)
	 * Positive atomic test
	 */
	@Test
	public void testGetPBCPositive() {
		try {
			int pbId = 1;
			int rbId = 2;
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBC(" + pbId + ", " + rbId + ") Positive " + spr);

			System.out.println("Receiving ProductBatchComponentDTO with pbId: " + pbId + ", rbId: " + rbId + ".");
			ProductBatchComponentDTO pbcDTO = pbcDAO.getProductBatchComponent(pbId, rbId);
			System.out.println("Received: " + pbcDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing ProductBatchComponentDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchComponentDAO.getPBC(pbId, rbId)
	 * Negative atomic test
	 */
	@Test
	public void testGetPBCNegative() {
		try {
			int pbId = 54;
			int rbId = 43;
			/* Make sure that the ProductBatchComponentDTO does not exist. */
			pbcDAO.deleteProductBatchComponent(pbId, rbId);

			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBC(" + pbId + ", " + rbId + ") Negative " + spr);

			System.out.println("Receiving ProductBatchComponentDTO with pbId: " + pbId + ", rbId: " + rbId + ".");
			ProductBatchComponentDTO pbcDTO = pbcDAO.getProductBatchComponent(pbId, rbId);
			System.out.println("Received: " + pbcDTO);

			fail("Failed: Received non-existent ProductBatchComponentDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchComponentDAO.getPBCList()
	 * Positive atomic test
	 */
	@Test
	public void testGetPBCListPositive() {
		try {
			List<ProductBatchComponentDTO> pbcList = pbcDAO.getProductBatchComponentList();
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBCList() Positive " + spr);
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
	 * Testing SQLProductBatchComponentDAO.getPBCList(rbId);
	 * Positive atomic test
	 */
	@Test
	public void testGetPBCListParameterPositive() {
		try {
			int rbId = 1;
			List<ProductBatchComponentDTO> pbcList = pbcDAO.getProductBatchComponentList(rbId);
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.getPBCList(" + rbId + ") Positive " + spr);
			for (int i = 0; i < pbcList.size(); i++) {
				System.out.println(i + ": " + pbcList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve SQLProductBatchComponentDAO.getPBCList(rbId)!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.createPBC(pbcDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreatePBCPositive() {
		try {
			/* Make sure the ProductBatchComponentDTO does not already exist. */
			int pbId = 1;
			int rbId = 2;
			pbcDAO.deleteProductBatchComponent(pbId, rbId);

			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.createPBC(pbcDTO) Positive " + spr);

			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 502.23, 43.23, 1);
			pbcDAO.createProductBatchComponent(pbcDTO);
			System.out.println("Created: " + pbcDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create pbcDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.createPBC(pbcDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreatePBCNegative() {
		try {
			int pbId = 1;
			int rbId = 2;
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.createPBC(pbcDTO) Negative " + spr);

			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 432.23, 23.23, 1);
			pbcDAO.createProductBatchComponent(pbcDTO);
			System.out.println("Created: " + pbcDTO);

			fail("Failed: Created already existing ProductBatchComponentDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchComponentDAO.updatePBC(pbcDTO)
	 * Positive atomic test
	 */
	@Test
	public void testUpdatePBCPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.updatePBC(pbcDTO) Positive " + spr);
			/* Creating pbcDTO to make sure that it exists. */
			int pbId = 2;
			int rbId = 3;
			pbcDAO.deleteProductBatchComponent(pbId, rbId);
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 99.99, 11.22, 1);
			pbcDAO.createProductBatchComponent(pbcDTO);

			double newTara = 23423.234;
			double newNetto = 555.342;

			ProductBatchComponentDTO updatedPbcDTO = new ProductBatchComponentDTO(pbId, rbId, newTara, newNetto, 1);

			System.out.println("Created: " + pbcDAO.getProductBatchComponent(pbId, rbId));
			pbcDAO.updateProductBatchComponent(updatedPbcDTO);
			System.out.println("Updated: " + pbcDAO.getProductBatchComponent(pbId, rbId));

			double expected = newTara;
			double actual = pbcDAO.getProductBatchComponent(pbId, rbId).getTara();

			assertEquals("Failed: The updated name does not match!", expected, actual, 0);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing ProductBatchComponentDTO");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchComponentDAO.deletePBC(pbId, rbId)
	 * Positive atomic test
	 */
	@Test
	public void testDeletePBCPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchComponentDAO.deletePBC(pbId, rbId) Positive " + spr);
			/* Creating ProductBatchComponentDTO to make sure that it exists. */
			int pbId = 2;
			int rbId = 3;
			pbcDAO.deleteProductBatchComponent(pbId, rbId);
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, 22.22, 33.33, 1);
			pbcDAO.createProductBatchComponent(pbcDTO);

			System.out.println("Created: " + pbcDTO);
			pbcDAO.deleteProductBatchComponent(pbId, rbId);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing ProductBatchComponentDTO!");
			System.out.println(lspr);
		}
	}

}