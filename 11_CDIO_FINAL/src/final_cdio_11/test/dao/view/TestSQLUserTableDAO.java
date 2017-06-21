package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLVUserTableDAO;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

public class TestSQLUserTableDAO {

	/*
	 * Required objects.
	 */
	private SQLVUserTableDAO userTableDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		userTableDAO = new SQLVUserTableDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		userTableDAO = null;
	}

	/* 
	 * Testing SQLAdminOperatorDAO.getVAdminOperator(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testGetVAdminOperatorPositive() {
		int oprId = 5;
		try {
			System.out.println("\n" + spr + " Testing SQLVUserTableDAO.getVUserTable(" + oprId + ") Positive " + spr);
			VUserTableDTO vUserTableDTO = userTableDAO.getVUserTable(oprId);

			boolean expected = false;
			boolean actual = false;

			if (vUserTableDTO == null) actual = true;

			System.out.println(vUserTableDTO);

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
			System.out.println("\n" + spr + " Testing SQLVUserTableDAO.getVUserTable(" + oprId + ") Negative " + spr);
			VUserTableDTO vUserTable = userTableDAO.getVUserTable(oprId);

			boolean expected = false;
			boolean actual = false;

			if (vUserTable == null) actual = true;

			System.out.println(vUserTable);

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
			System.out.println("\n" + spr + " Testing SQLVUserTableDAO.getVUserTableList() Positive " + spr);
			List<VUserTableDTO> dtoList = userTableDAO.getVUserTableList();

			boolean expected = false;
			boolean actual = false;

			for (VUserTableDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VUserTableDTO dto : dtoList) {
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