package test.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLProductBatchDAO;
import data.dto.ProductBatchDTO;

public class TestSQLProductBatchDAO {

	/*
	 * Required objects.
	 */
	private SQLProductBatchDAO pbDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the ProductBatchDAO.
	 */
	@Before
	public void setUp() {
		pbDAO = new SQLProductBatchDAO(Connector.getInstance());
	}

	/*
	 * Resetting the ProductBatchDAO.
	 */
	@After
	public void tearDown() {
		pbDAO = null;
	}

	/* 
	 * Testing SQLProductBatchDAO.getProductBatch(pbId)
	 * Positive atomic test
	 */
	@Test
	public void testGetProductBatchPositive() {
		try {
			int pbId = 1;
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.getProductBatch(" + pbId + ") Positive " + spr);

			System.out.println("Receiving ProductBatchDTO with pbId: " + pbId + ".");
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			System.out.println("Received: " + pbDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing ProductBatchDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchDAO.getProductBatch(pbId)
	 * Negative atomic test
	 */
	@Test
	public void testGetProductBatchNegative() {
		try {
			int pbId = 102;

			/* Make sure that the ProductBatchDTO does not exist. */
			pbDAO.deleteProductBatch(pbId);

			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.getProductBatch(" + pbId + ") Negative " + spr);

			System.out.println("Receiving ProductBatchDTO with pbId: " + pbId + ".");
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			System.out.println("Received: " + pbDTO);

			fail("Failed: Received non-existent ProductBatchDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchDAO.getProductBatchList()
	 * Positive atomic test
	 */
	@Test
	public void testGetProductBatchListPositive() {
		try {
			List<ProductBatchDTO> pbList = pbDAO.getProductBatchList();
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.getProductBatchList() Positive " + spr);
			for (int i = 0; i < pbList.size(); i++) {
				System.out.println(i + ": " + pbList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to retrieve ProductBatchList!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchDAO.createProductBatch(pbDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreateProductBatchPositive() {
		try {
			/* Make sure the ProductBatchDTO does not already exist. */
			int pbId = 9;
			pbDAO.deleteProductBatch(pbId);

			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.createProductBatch(pbDTO) Positive " + spr);
			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 2223, 1);

			pbDAO.createProductBatch(pbDTO);
			System.out.println("Created: " + pbDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create ProductBatchDTO!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchDAO.createProductBatch(pbDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreateProductBatchNegative() {
		try {
			int pbId = 1;
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.createProductBatch(pbDTO) Negative " + spr);
			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 2223, 1);

			pbDAO.createProductBatch(pbDTO);
			System.out.println("Created: " + pbDTO);

			fail("Failed: Created already existing ProductBatchDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLProductBatchDAO.updateProductBatch(pbDTO)
	 * Positive atomic test
	 */
	@Test
	public void testUpdateProductBatchPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.updateProductBatch(pbDTO) Positive " + spr);
			/* Creating pbDTO to make sure that it exists. */
			int pbId = 43;
			pbDAO.deleteProductBatch(pbId);
			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 22235323, 1);
			pbDAO.createProductBatch(pbDTO);

			int newStatus = 9393;

			ProductBatchDTO updatedPbDTO = new ProductBatchDTO(pbId, newStatus, 1);

			System.out.println("Created: " + pbDAO.getProductBatch(pbId));
			pbDAO.updateProductBatch(updatedPbDTO);
			System.out.println("Updated: " + pbDAO.getProductBatch(pbId));

			int expected = newStatus;
			int actual = pbDAO.getProductBatch(pbId).getStatus();

			assertEquals("Failed: The updated status does not match!", expected, actual);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing ProductBatchDTO");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchDAO.deleteProductBatch(pbId)
	 * Positive atomic test
	 */
	@Test
	public void testDeleteProductBatchPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.deleteProductBatch(pbId) Positive " + spr);
			/* Creating ProductBatchDTO to make sure that it exists. */
			int pbId = 81;
			pbDAO.deleteProductBatch(pbId);
			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 93459345, 1);
			pbDAO.createProductBatch(pbDTO);

			System.out.println("Created: " + pbDTO);
			pbDAO.deleteProductBatch(pbId);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing ProductBatchDTO!");
			System.out.println(lspr);
		}
	}

}