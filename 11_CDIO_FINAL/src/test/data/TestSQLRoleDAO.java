package test.data;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLRoleDAO;
import data.dto.RoleDTO;

public class TestSQLRoleDAO {

	/*
	 * Required objects.
	 */
	private SQLRoleDAO roleDAO;
	private final String spr = "#############";
	private final String lspr = spr + spr + spr + spr + spr + spr;

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
	 * Testing SQLRoleDAO.getOprRoles(oprId)
	 * Positive atomic test
	 */
	@Test
	public void testGetOprRolesPositive() {
		try {
			int oprId = 10;
			List<RoleDTO> oprRoleList = roleDAO.getOprRoles(oprId);
			System.out.println("\n" + spr + " Testing SQLRoleDAO.getOprRoles(" + oprId + ") Positive " + spr);

			System.out.println("Receiving roleList with oprId: " + oprId);
			for (int i = 0; i < oprRoleList.size(); i++) {
				System.out.println(i + ": " + oprRoleList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to receive oprRoleList!");
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRoleDAO.getOprRoles(oprId)
	 * Negative atomic test
	 */
	@Test
	public void testGetOprRolesNegative() {
		try {
			int oprId = 4353;
			System.out.println("\n" + spr + " Testing SQLRoleDAO.getOprRoles(" + oprId + ") Negative " + spr);
			List<RoleDTO> oprRoleList = roleDAO.getOprRoles(oprId);

			System.out.println("Receiving roleList with oprId: " + oprId);
			for (int i = 0; i < oprRoleList.size(); i++) {
				System.out.println(i + ": " + oprRoleList.get(i));
			}
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/* 
	 * Testing SQLRoleDAO.getRoleList()
	 * Positive atomic test
	 */
	@Test
	public void testGetRoleListPositive() {
		try {
			List<RoleDTO> oprRoleList = roleDAO.getRoleList();
			System.out.println("\n" + spr + " Testing SQLRoleDAO.getRoleList() Positive " + spr);

			System.out.println("Receiving roleList of all roles.");
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
	 * Testing SQLRoleDAO.createRole(roleDTO)
	 * Positive atomic test
	 */
	@Test
	public void testCreateRolePositive() {
		try {
			/* Make sure the RoleDTO does not already exist. */
			roleDAO.deleteRole(new RoleDTO(1, "Admin"));

			System.out.println("\n" + spr + " Testing SQLRoleDAO.createRole(roleDTO) Positive " + spr);

			RoleDTO roleDTO = new RoleDTO(1, "Admin");
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
	 * Testing SQLRoleDAO.createRole(roleDTO)
	 * Negative atomic test
	 */
	@Test
	public void testCreateRoleNegative() {
		try {
			System.out.println("\n" + spr + " Testing SQLRoleDAO.createRole(roleDTO) Negative " + spr);

			RoleDTO roleDTO = new RoleDTO(1, "Operator");
			roleDAO.createRole(roleDTO);
			System.out.println("Created: " + roleDTO);

			fail("Failed: Created already existing roleDTO!");
			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			System.out.println(lspr);
		}
	}

	/*
	 * Testing SQLRoleDAO.deleteRole(RoleDTO)
	 * Positive atomic test
	 */
	@Test
	public void testDeleteOperatorPositive() {
		try {
			System.out.println("\n" + spr + " Testing SQLRoleDAO.deleteRole(RoleDTO) Positive " + spr);
			/* Creating RoleDTO to make sure that it exists. */
			RoleDTO roleDTO = new RoleDTO(3, "Pharmacist");
			roleDAO.deleteRole(roleDTO);
			roleDAO.createRole(roleDTO);

			System.out.println("Created: " + roleDTO);
			roleDAO.deleteRole(roleDTO);
			System.out.println("Deleted.");

			System.out.println(lspr);
		} catch (DALException e) {
			System.out.println(e.getMessage());
			fail("Failed: Failed to delete existing RoleDTO!");
			System.out.println(lspr);
		}
	}

}