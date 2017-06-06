package final_cdio_11.test.validator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.validator.IRoleValidator;
import final_cdio_11.java.data.validator.RoleValidator;

public class TestRoleValidator {

	private IRoleValidator roleValidator;
	
	@Before
	public void setUp() throws Exception {
		roleValidator = new RoleValidator();
	}

	@After
	public void tearDown() throws Exception {
		roleValidator = null;
	}
	
	/*
	 * oprIdIsValid tests.
	 */
	
	@Test
	public void testIsOprIdValidPositive00() {
		int oprId = 2374224;

		boolean actual = roleValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidPositive01() {
		int oprId = 1;

		boolean actual = roleValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidPositive02() {
		int oprId = 99999999;

		boolean actual = roleValidator.isOprIdValid(oprId);
		boolean expected = true;

		assertEquals("Failed: " + oprId + " is supposed to be valid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative00() {
		int oprId = 942034029;

		boolean actual = roleValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative01() {
		int oprId = 0;

		boolean actual = roleValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}

	@Test
	public void testIsOprIdValidNegative02() {
		int oprId = -1;

		boolean actual = roleValidator.isOprIdValid(oprId);
		boolean expected = false;

		assertEquals("Failed: " + oprId + " is supposed to be invalid.", expected, actual);
	}
	
	/*
	 * RoleNameValid tests.
	 */
	@Test
	public void testIsRoleNameValidPositive00() {
		String roleName = Role.Admin.toString();

		boolean actual = roleValidator.isRoleNameValid(roleName);
		boolean expected = true;

		assertEquals("Failed: " + roleName + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsRoleNameValidPositive01() {
		String roleName = Role.Farmaceut.toString();

		boolean actual = roleValidator.isRoleNameValid(roleName);
		boolean expected = true;

		assertEquals("Failed: " + roleName + " is supposed to be valid.", expected, actual);
	}
	@Test
	public void testIsRoleNameValidPositive02() {
		String roleName = Role.Værkfører.toString();

		boolean actual = roleValidator.isRoleNameValid(roleName);
		boolean expected = true;

		assertEquals("Failed: " + roleName + " is supposed to be valid.", expected, actual);
	}
	
	@Test
	public void testIsRoleNameValidNegative00() {
		
		String roleName = "aAdmin";
		
		boolean actual = roleValidator.isRoleNameValid(roleName);
		boolean expected = false;

		assertEquals("Failed: " + roleName+ " is supposed to be invalid.", expected, actual);
	}


}
