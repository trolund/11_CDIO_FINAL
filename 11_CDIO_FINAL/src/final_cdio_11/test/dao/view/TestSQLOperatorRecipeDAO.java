package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLOperatorRecipeDAO;
import final_cdio_11.java.data.dto.view.VOperatorRecipeDTO;

public class TestSQLOperatorRecipeDAO {

	/*
	 * Required objects.
	 */
	private SQLOperatorRecipeDAO oprRecipeDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		oprRecipeDAO = new SQLOperatorRecipeDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		oprRecipeDAO = null;
	}

	/* 
	 * Testing SQLOperatorRecipeDAO.getVOperatorRecipe(receptId)
	 * Positive atomic test
	 */
	@Test
	public void testGetVOperatorRecipePositive() {
		int receptId = 3;
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorRecipeDAO.getVOperatorRecipe(" + receptId + ") Positive " + spr);
			List<VOperatorRecipeDTO> dtoList = oprRecipeDAO.getVOperatorRecipe(receptId);

			boolean expected = false;
			boolean actual = false;

			for (VOperatorRecipeDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VOperatorRecipeDTO dto : dtoList) {
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
	 * Testing SQLOperatorRecipeDAO.getVOperatorRecipe(receptId)
	 * Negative atomic test
	 */
	@Test
	public void testGetVOperatorRecipeNegative() {
		int receptId = 332;
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorRecipeDAO.getVOperatorRecipe(" + receptId + ") Negative " + spr);
			List<VOperatorRecipeDTO> dtoList = oprRecipeDAO.getVOperatorRecipe(receptId);

			boolean expected = false;
			boolean actual = false;

			for (VOperatorRecipeDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VOperatorRecipeDTO dto : dtoList) {
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
	 * Testing SQLOperatorRecipeDAO.getVOperatorRecipeList()
	 * Positive atomic test
	 */
	@Test
	public void testGetVOperatorRecipeListPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorRecipeDAO.getVOperatorRecipeList() Positive " + spr);
			List<VOperatorRecipeDTO> dtoList = oprRecipeDAO.getVOperatorRecipeList();

			boolean expected = false;
			boolean actual = false;

			for (VOperatorRecipeDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VOperatorRecipeDTO dto : dtoList) {
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