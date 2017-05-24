package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLAdminOperatorDAO;
import final_cdio_11.java.data.dto.view.VAdminOperatorDTO;

public class TestSQLAdminOperatorDAO {

	/*
	 * Required objects.
	 */
	private SQLAdminOperatorDAO adminOprDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		adminOprDAO = new SQLAdminOperatorDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		adminOprDAO = null;
	}

	/* 
	 * Testing SQLAdminOperatorDAO.getVAdminOperator(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testGetVAdminOperatorPositive() {
		int oprId = 5;
		try {
			System.out.println("\n" + spr + " Testing SQLAdminOperatorDAO.getVAdminOperator(" + oprId + ") Positive " + spr);
			VAdminOperatorDTO dto = adminOprDAO.getVAdminOperator(oprId);

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
	 * Testing SQLAdminOperatorDAO.getVAdminOperator(oprId)
	 * Negative atomic test
	 */
	@Test
	public void testGetVAdminOperatorNegative() {
		int oprId = 5234;
		try {
			System.out.println("\n" + spr + " Testing SQLAdminOperatorDAO.getVAdminOperator(" + oprId + ") Negative " + spr);
			VAdminOperatorDTO dto = adminOprDAO.getVAdminOperator(oprId);

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
	 * Testing SQLAdminOperatorDAO.getVAdminOperatorList()
	 * Positive atomic test
	 */
	@Test
	public void testGetVAdminOperatorListPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLAdminOperatorDAO.getVAdminOperatorList() Positive " + spr);
			List<VAdminOperatorDTO> dtoList = adminOprDAO.getVAdminOperatorList();

			boolean expected = false;
			boolean actual = false;

			for (VAdminOperatorDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VAdminOperatorDTO dto : dtoList) {
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