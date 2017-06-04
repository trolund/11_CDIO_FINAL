package final_cdio_11.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dto.ProductBatchDTO;

public class TestSQLProductBatchDAO {

	/*
	 * Required objects.
	 */
	private IProductBatchDAO pbDAO;
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
	 * Testing SQLProductBatchDAO.getProductBatch(pbId) Positive atomic test
	 */
	@Test
	public void testGetProductBatchPositive() {
		try {
			int pbId = 1;
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.getProductBatch(" + pbId + ") Positive " + spr);

			System.out.println("Receiving ProductBatchDTO with pbId: " + pbId + ".");
			ProductBatchDTO newPbDTO = pbDAO.getProductBatch(pbId);
			System.out.println("Received: " + newPbDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing ProductBatchDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchDAO.getProductBatch(pbId) Negative atomic test
	 */
	@Test
	public void testGetProductBatchNegative() {
		try {
			int pbId = 10322;

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
	 * Testing SQLProductBatchDAO.getProductBatchList() Positive atomic test
	 */
	@Test
	public void testGetProductBatchListPositive() {
		try {
			List<ProductBatchDTO> pbList = pbDAO.getProductBatchList();
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.getProductBatchList() Positive " + spr);

			if (pbList == null) fail("Failed: Failed to retrieve ProductBatchList!");

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
	 * Testing SQLProductBatchDAO.createProductBatch(pbDTO) Positive atomic test
	 */
	@Test
	public void testCreateProductBatchPositive() {
		try {
			int pbId = 7;

			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.createProductBatch(pbDTO) Positive " + spr);
			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 2223, 1, 0);

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
	 * Testing SQLProductBatchDAO.createProductBatch(pbDTO) Negative atomic test
	 */
	@Test
	public void testCreateProductBatchNegative() {
		try {
			int pbId = 1;

			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 2223, 1, 0);

			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.createProductBatch(pbDTO) Negative " + spr);
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
	 * Testing SQLProductBatchDAO.updateProductBatch(pbDTO) Positive atomic test
	 */
	@Test
	public void testUpdateProductBatchPositive() {
		try {
			int pbId = 2;

			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.updateProductBatch(pbDTO) Positive " + spr);

			int newStatus = 9393;

			ProductBatchDTO updatedPbDTO = new ProductBatchDTO(pbId, newStatus, 1, 0);

			System.out.println("Created: " + pbDAO.getProductBatch(pbId));
			pbDAO.updateProductBatch(updatedPbDTO);
			System.out.println("Updated: " + pbDAO.getProductBatch(pbId));

			int expected = newStatus;
			int actual = pbDAO.getProductBatch(pbId).getItemStatus();

			assertEquals("Failed: The updated status does not match!", expected, actual);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to update existing ProductBatchDTO");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLProductBatchDAO.deleteProductBatch(pbId) Positive atomic test
	 */
	@Test
	public void testDeleteProductBatchPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLProductBatchDAO.deleteProductBatch(pbId) Positive " + spr);
			int pbId = 3;

			ProductBatchDTO pbDTO = new ProductBatchDTO(pbId, 2223, 1, 0);

			System.out.println("Created: " + pbDTO);
			pbDAO.deleteProductBatch(pbId);
			System.out.println("Deleted.");

			if (pbDAO.getProductBatch(pbId).getStatus() != 1) fail("Failed: Failed to delete existing ProductBatchDTO!");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing ProductBatchDTO!");
			System.out.println(lspr);
		}
	}

}