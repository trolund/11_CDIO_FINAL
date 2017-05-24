package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLPharmacistRecipeDAO;
import final_cdio_11.java.data.dto.view.VPharmacistRecipeDTO;

public class TestSQLPharmacistRecipeDAO {

	/*
	 * Required objects.
	 */
	private SQLPharmacistRecipeDAO pharmacistRecipeDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		pharmacistRecipeDAO = new SQLPharmacistRecipeDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		pharmacistRecipeDAO = null;
	}

	/* 
	 * Testing SQLPharmacistRecipeDAO.getVPharmacistRecipe(receptId)
	 * Positive atomic test
	 */
	@Test
	public void testGetVPharmacistRecipePositive() {
		int receptId = 3;
		try {
			System.out.println("\n" + spr + " Testing SQLPharmacistRecipeDAO.getVPharmacistRecipe(" + receptId + ") Positive " + spr);
			List<VPharmacistRecipeDTO> dtoList = pharmacistRecipeDAO.getVPharmacistRecipe(receptId);

			boolean expected = false;
			boolean actual = false;

			for (VPharmacistRecipeDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VPharmacistRecipeDTO dto : dtoList) {
				System.out.println(dto);
			}

			assertEquals("Failed: null object found in dtoList!", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLPharmacistRecipeDAO.getVPharmacistRecipe(receptId)
	 * Negative atomic test
	 */
	@Test
	public void testGetVPharmacistRecipeNegative() {
		int receptId = 343;
		try {
			System.out.println("\n" + spr + " Testing SQLPharmacistRecipeDAO.getVPharmacistRecipe(" + receptId + ") Negative " + spr);
			List<VPharmacistRecipeDTO> dtoList = pharmacistRecipeDAO.getVPharmacistRecipe(receptId);

			boolean expected = false;
			boolean actual = false;

			for (VPharmacistRecipeDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VPharmacistRecipeDTO dto : dtoList) {
				System.out.println(dto);
			}

			assertEquals("Failed: null object found in dtoList!", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLPharmacistRecipeDAO.getVPharmacistRecipeList()
	 * Positive atomic test
	 */
	@Test
	public void testGetVPharmacistRecipeListPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLPharmacistRecipeDAO.getVPharmacistRecipeList() Positive " + spr);
			List<VPharmacistRecipeDTO> dtoList = pharmacistRecipeDAO.getVPharmacistRecipeList();

			boolean expected = false;
			boolean actual = false;

			for (VPharmacistRecipeDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VPharmacistRecipeDTO dto : dtoList) {
				System.out.println(dto);
			}

			assertEquals("Failed: null object found in dtoList!", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

}