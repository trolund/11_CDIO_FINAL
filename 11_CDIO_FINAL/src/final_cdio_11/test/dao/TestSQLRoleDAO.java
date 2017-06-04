package final_cdio_11.test.dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.data.dto.RoleDTO;
import final_cdio_11.java.utils.TextHandler;

public class TestSQLRoleDAO {

	/*
	 * Required objects.
	 */
	private IRoleDAO roleDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;
	private final TextHandler textHandler = TextHandler.getInstance();

	/*
	 * Setting up the RoleDAO.
	 */
	@Before
	public void setUp() {
		roleDAO = new SQLRoleDAO(Connector.getInstance());
	}

	/*
	 * Resetting the RoleDAO.
	 */
	@After
	public void tearDown() {
		roleDAO = null;
	}

	/*
	 * Testing SQLRoleDAO.getOprRoles(oprId) Positive atomic test
	 */
	@Test
	public void testGetOprRolesPositive() {
		try {
			int oprId = 1;

			List<RoleDTO> oprRoleList = roleDAO.getOprRoles(oprId);
			System.out.println("\n" + spr + " Testing SQLRoleDAO.getOprRoles(" + oprId + ") Positive " + spr);

			System.out.println("Receiving roleList with oprId: " + oprId);
			for (int i = 0; i < oprRoleList.size(); i++) {
				System.out.println(i + ": " + oprRoleList.get(i));
			}

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive existing RoleDTO's!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRoleDAO.getOprRoles(oprId) Negative atomic test
	 */
	@Test
	public void testGetOprRolesNegative() {
		try {
			int oprId = 13543;

			System.out.println("\n" + spr + " Testing SQLRoleDAO.getOprRoles(" + oprId + ") Negative " + spr);
			List<RoleDTO> oprRoleList = roleDAO.getOprRoles(oprId);

			if (oprRoleList == null) fail("Failed: null list received!");

			System.out.println("Receiving roleList with oprId: " + oprId);
			for (int i = 0; i < oprRoleList.size(); i++) {
				System.out.println(i + ": " + oprRoleList.get(i));
			}

			fail("Failed: Received non-existing RoleDTO's!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRoleDAO.getRoleList() Positive atomic test
	 */
	@Test
	public void testGetRoleListPositive() {
		try {

			List<RoleDTO> oprRoleList = roleDAO.getRoleList();
			System.out.println("\n" + spr + " Testing SQLRoleDAO.getRoleList() Positive " + spr);

			System.out.println("Receiving roleList of all roles.");

			if (oprRoleList == null) fail("Failed: null list received!");

			for (int i = 0; i < oprRoleList.size(); i++) {
				System.out.println(i + ": " + oprRoleList.get(i));
			}

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive roleList!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRoleDAO.createRole(roleDTO) Positive atomic test
	 */
	@Test
	public void testCreateRolePositive() {
		try {
			int oprId = 4;

			System.out.println("\n" + spr + " Testing SQLRoleDAO.createRole(roleDTO) Positive " + spr);

			RoleDTO roleDTO = new RoleDTO(oprId, textHandler.ROLE_ADMIN, 0);
			roleDAO.createRole(roleDTO);
			System.out.println("Created: " + roleDTO);

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to create roleDTO!");
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRoleDAO.createRole(roleDTO) Negative atomic test
	 */
	@Test
	public void testCreateRoleNegative() {
		try {
			int oprId = 10;
			System.out.println("\n" + spr + " Testing SQLRoleDAO.createRole(roleDTO) Negative " + spr);

			RoleDTO roleDTO = new RoleDTO(oprId, textHandler.ROLE_ADMIN, 0);
			roleDAO.createRole(roleDTO);
			System.out.println("Created: " + roleDTO);

			fail("Failed: Created already existing roleDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

}