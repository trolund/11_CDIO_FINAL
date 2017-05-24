package final_cdio_11.test.dao.view;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.SQLOperatorRBDAO;
import final_cdio_11.java.data.dto.view.VOperatorRBDTO;

public class TestSQLOperatorRBDAO {

	/*
	 * Required objects.
	 */
	private SQLOperatorRBDAO oprRBDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

	/*
	 * Setting up the View DAO.
	 */
	@Before
	public void setUp() {
		oprRBDAO = new SQLOperatorRBDAO(Connector.getInstance());
	}

	/*
	 * Resetting the View DAO.
	 */
	@After
	public void tearDown() {
		oprRBDAO = null;
	}

	/* 
	 * Testing SQLOperatorRBDAO.getVOperatorRB(raavareId)
	 * Positive atomic test
	 */
	@Test
	public void getVOperatorRBPositive() {
		int raavareId = 3;
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorRBDAO.getVOperatorRB(" + raavareId + ") Positive " + spr);
			List<VOperatorRBDTO> dtoList = oprRBDAO.getVOperatorRB(raavareId);

			boolean expected = false;
			boolean actual = false;

			for (VOperatorRBDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VOperatorRBDTO dto : dtoList) {
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
	 * Testing SQLOperatorRBDAO.getVOperatorRB(raavareId)
	 * Negative atomic test
	 */
	@Test
	public void getVOperatorRBNegative() {
		int raavareId = 233;
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorRBDAO.getVOperatorRB(" + raavareId + ") Negative " + spr);
			List<VOperatorRBDTO> dtoList = oprRBDAO.getVOperatorRB(raavareId);

			boolean expected = false;
			boolean actual = false;

			for (VOperatorRBDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VOperatorRBDTO dto : dtoList) {
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
	 * Testing SQLOperatorRBDAO.getVOperatorRBList()
	 * Positive atomic test
	 */
	@Test
	public void getVOperatorRBListPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLOperatorRBDAO.getVOperatorRBList() Positive " + spr);
			List<VOperatorRBDTO> dtoList = oprRBDAO.getVOperatorRBList();

			boolean expected = false;
			boolean actual = false;

			for (VOperatorRBDTO dto : dtoList) {
				if (dto == null) actual = true;
			}

			for (VOperatorRBDTO dto : dtoList) {
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