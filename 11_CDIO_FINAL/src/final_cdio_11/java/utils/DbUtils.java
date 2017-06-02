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
		connector.getConnection().prepareStatement("DELETE FROM rolle WHERE opr_id != 10 AND opr_id != 1 AND opr_id != 2 AND opr_id != 3").executeUpdate();
		connector.getConnection().prepareStatement("DELETE FROM operatoer WHERE opr_id != 10 AND opr_id != 1 AND opr_id != 2 AND opr_id != 3").executeUpdate();
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
		
		System.err.println("Done.");
	}

}