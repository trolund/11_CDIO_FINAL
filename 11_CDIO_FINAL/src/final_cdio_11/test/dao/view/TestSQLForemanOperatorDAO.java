package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLForemanOperatorDAO;
import final_cdio_11.java.data.dto.view.VForemanOperatorDTO;

public class TestSQLForemanOperatorDAO {

	/*
	 * Required objects.
	 */
	private SQLForemanOperatorDAO foremanOprDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		foremanOprDAO = new SQLForemanOperatorDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		foremanOprDAO = null;
	}

	/* 
	 * Testing SQLForemanOperatorDAO.getVForemanOperator(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testGetVForemanOperatorPositive() {
		int oprId = 5;
		try {
			System.out.println("\n" + spr + " Testing SQLForemanOperatorDAO.getVForemanOperator(" + oprId + ") Positive " + spr);
			VForemanOperatorDTO dto = foremanOprDAO.getVForemanOperator(oprId);

			boolean expected = false;
			boolean actual = false;

			if (dto == null) actual = true;

			System.out.println(dto);

			assertEquals("Failed: null object returned.", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLForemanOperatorDAO.getVForemanOperator(oprId)
	 * Negative atomic test
	 */
	@Test
	public void testGetVForemanOperatorNegative() {
		int oprId = 5234;
		try {
			System.out.println("\n" + spr + " Testing SQLForemanOperatorDAO.getVForemanOperator(" + oprId + ") Negative " + spr);
			VForemanOperatorDTO dto = foremanOprDAO.getVForemanOperator(oprId);

			boolean expected = false;
			boolean actual = false;

			if (dto == null) actual = true;

			System.out.println(dto);

			assertEquals("Failed: null object returned.", expected, actual);
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLForemanOperatorDAO.getVForemanOperatorList()
	 * Positive atomic test
	 */
	@Test
	public void testGetVForemanOperatorListPositive() {
		try {
			System.out.println("\n" + spr + " SQLForemanOperatorDAO.getVForemanOperatorList() Positive " + spr);
			List<VForemanOperatorDTO> dtoList = foremanOprDAO.getVForemanOperatorList();

			boolean expected = false;
			boolean actual = false;

			for (VForemanOperatorDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VForemanOperatorDTO dto : dtoList) {
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