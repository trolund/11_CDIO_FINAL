package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLAdminForemanPBCDAO;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;

public class TestSQLAdminForemanPBCDAO {

	/*
	 * Required objects.
	 */
	private SQLAdminForemanPBCDAO adminForemanPBCDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		adminForemanPBCDAO = new SQLAdminForemanPBCDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		adminForemanPBCDAO = null;
	}

	/* 
	 * Testing SQLAdminForemanPBCDAO.getVAdminForemanPBC(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testGetVAdminForemanPBCPositive() {
		int oprId = 1;
		try {
			System.out.println("\n" + spr + " Testing SQLADminForemanPBCDAO.getVAdminForemanPBC(" + oprId + ") Positive " + spr);
			List<VAdminForemanPBCDTO> dtoList = adminForemanPBCDAO.getVAdminForemanPBC(oprId);

			boolean expected = false;
			boolean actual = false;

			for (VAdminForemanPBCDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VAdminForemanPBCDTO dto : dtoList) {
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
	 * Testing SQLAdminForemanPBCDAO.getVAdminForemanPBC(oprId)
	 * Negative atomic test
	 */
	@Test
	public void testGetVAdminForemanPBCNegative() {
		int oprId = 324234;
		try {
			System.out.println("\n" + spr + " Testing SQLADminForemanPBCDAO.getVAdminForemanPBC(" + oprId + ") Negative " + spr);
			List<VAdminForemanPBCDTO> dtoList = adminForemanPBCDAO.getVAdminForemanPBC(oprId);

			boolean expected = false;
			boolean actual = false;

			for (VAdminForemanPBCDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VAdminForemanPBCDTO dto : dtoList) {
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
	 * Testing SQLAdminForemanPBCDAO.getVAdminForemanPBCList()
	 * Positive atomic test
	 */
	@Test
	public void testGetVAdminForemanPBCListPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLADminForemanPBCDAO.getVAdminForemanPBCList() Positive " + spr);
			List<VAdminForemanPBCDTO> dtoList = adminForemanPBCDAO.getVAdminForemanPBCList();

			boolean expected = false;
			boolean actual = false;

			for (VAdminForemanPBCDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VAdminForemanPBCDTO dto : dtoList) {
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