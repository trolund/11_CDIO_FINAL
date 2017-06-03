package final_cdio_11.java.utils;

import java.sql.SQLException;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;

public class DbUtils {

	private static final Connector connector = Connector.getInstance();
	private static final TextHandler textHandler = TextHandler.getInstance();

	public static void main(String[] args) {
		try {
			deleteAllUsers();
			createSampleUsers();
		} catch (SQLException | DALException e) {
			e.printStackTrace();
		}
	}

	private static void deleteAllUsers() throws SQLException {
		System.err.println("Deleting all users....");
		connector.getConnection().prepareStatement("DELETE FROM rolle").executeUpdate();
		connector.getConnection().prepareStatement("DELETE FROM operatoer").executeUpdate();
		System.err.println("Done.");
	}

	private static void createSampleUsers() throws DALException {
		IOperatorDAO oprDAO = new SQLOperatorDAO(connector);
		IRoleDAO roleDAO = new SQLRoleDAO(connector);

		System.err.println("Creating standard users....");

		// Create Admin
		oprDAO.createOperator(new OperatorDTO(10, "Admin", "Admin", "ADMIN", "webadmin@group11.com", "123456-7890", "root", 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Admin...");

		// Create Stig 
		oprDAO.createOperator(new OperatorDTO(1, "Stig", "Høgh", "SH", "shg@dtu.dk", "098765-4321", "rootstig", 0));
		roleDAO.createRole(new RoleDTO(1, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(1, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(1, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Stig...");

		// Create Finn
		oprDAO.createOperator(new OperatorDTO(2, "Finn", "Gustafsson", "FG", "fiu@dtu.dk", "135792-4680", "rootfinn", 0));
		roleDAO.createRole(new RoleDTO(2, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(2, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Finn...");

		// Create Ronnie
		oprDAO.createOperator(new OperatorDTO(3, "Ronnie", "Dalsgaard", "RD", "ronniedalsgaard@gmail.com", "A1B2C3-D4E5", "rootronnie", 0));
		roleDAO.createRole(new RoleDTO(3, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Ronnie...");

		// Create Christian
		oprDAO.createOperator(new OperatorDTO(4, "Christian", "Budtz", "CB", "s136400@student.dtu.dk", "Z1Q2E3-V4Z5", "rootchristian", 0));
		roleDAO.createRole(new RoleDTO(4, textHandler.ROLE_FARMACEUT, 0));
		System.err.println("Created Christian...");

		// Create Troels
		oprDAO.createOperator(new OperatorDTO(5, "Troels", "Lund", "TL", "trolund@gmail.com", "TR0LL3-HTLM", "roottroels", 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Troels...");

		// Create Thomas
		oprDAO.createOperator(new OperatorDTO(6, "Thomas", "Mascagni", "TM", "thomes@gmail.com", "1T4LYZ-M0II", "rootthomas", 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Thomas...");

		// Create Behnia
		oprDAO.createOperator(new OperatorDTO(7, "Behnia", "Farazan", "BF", "gunit@gmail.com", "H4CK3R-H4CK", "rootbehnia", 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Behnia...");

		// Create Daniel A.
		oprDAO.createOperator(new OperatorDTO(8, "Daniel", "Anusic", "DA", "dannyboi@gmail.com", "H1TM4N-W1L7", "rootdaniel", 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Daniel A...");

		// Create Daniel L.
		oprDAO.createOperator(new OperatorDTO(9, "Daniel", "Larsen", "DL", "iyyelsec@gmail.com", "4M4Z1N-C0D3", "rootdaniel", 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Daniel L...");

		// Create Helene
		oprDAO.createOperator(new OperatorDTO(11, "Helene", "Zgaya", "HZ", "heleneyo@gmail.com", "ST4RF4-W4RZ0", "roothelene", 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_VAERK, 0));
		System.err.println("Created Helene...");

		System.err.println("Done.");
	}

}