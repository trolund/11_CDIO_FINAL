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
	private static final Utils utils = Utils.getInstance();

	public static void main(String[] args) {
		try {
			resetDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

	private static void resetDatabase() throws DALException, SQLException {
		utils.logMessage("WARNING: Database is getting reset!");
		deleteProduktBatchKomponent();
		deleteProduktBatch();
		deleteRaavareBatch();
		deleteReceptKomponent();
		deleteRecept();
		deleteRaavare();
		deleteOperators();
		createSampleOperators();
		createSampleRaavare();
		createSampleRaavareBatch();
		createSampleRecept();
		createSampleReceptKomponent();
		createSampleProduktBatch();
		createSampleProduktBatchKomponent();
		utils.logMessage("WARNING: Database was reset successfully!");
	}

	private static void createSampleOperators() throws DALException {
		IOperatorDAO oprDAO = new SQLOperatorDAO(connector);
		IRoleDAO roleDAO = new SQLRoleDAO(connector);

		utils.logMessage("Creating sample operators...");

		// Create Admin
		oprDAO.createOperator(new OperatorDTO(10, "Admin", "Admin", "ADMIN", "webadmin@group11.com", "123456-7890", "root", 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(10, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Admin...");

		// Create Stig 
		oprDAO.createOperator(new OperatorDTO(1, "Stig", "Høgh", "SH", "shg@dtu.dk", "098765-4321", "rootstig", 0));
		roleDAO.createRole(new RoleDTO(1, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(1, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(1, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Stig...");

		// Create Finn
		oprDAO.createOperator(new OperatorDTO(2, "Finn", "Gustafsson", "FG", "fiu@dtu.dk", "135792-4680", "rootfinn", 0));
		roleDAO.createRole(new RoleDTO(2, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(2, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Finn...");

		// Create Ronnie
		oprDAO.createOperator(new OperatorDTO(3, "Ronnie", "Dalsgaard", "RD", "ronniedalsgaard@gmail.com", "A1B2C3-D4E5", "rootronnie", 0));
		roleDAO.createRole(new RoleDTO(3, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Ronnie...");

		// Create Christian
		oprDAO.createOperator(new OperatorDTO(4, "Christian", "Budtz", "CB", "s136400@student.dtu.dk", "Z1Q2E3-V4Z5", "rootchristian", 0));
		roleDAO.createRole(new RoleDTO(4, textHandler.ROLE_FARMACEUT, 0));
		utils.logMessage("Created operator Christian...");

		// Create Troels
		oprDAO.createOperator(new OperatorDTO(5, "Troels", "Lund", "TL", "trolund@gmail.com", "TR0LL3-HTLM", "roottroels", 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(5, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Troels...");

		// Create Thomas
		oprDAO.createOperator(new OperatorDTO(6, "Thomas", "Mascagni", "TM", "thomes@gmail.com", "1T4LYZ-M0II", "rootthomas", 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(6, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Thomas...");

		// Create Behnia
		oprDAO.createOperator(new OperatorDTO(7, "Behnia", "Farazan", "BF", "gunit@gmail.com", "H4CK3R-H4CK", "rootbehnia", 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(7, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Behnia...");

		// Create Daniel A.
		oprDAO.createOperator(new OperatorDTO(8, "Daniel", "Anusic", "DA", "dannyboi@gmail.com", "H1TM4N-W1L7", "rootdaniel", 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(8, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Daniel A...");

		// Create Daniel L.
		oprDAO.createOperator(new OperatorDTO(9, "Daniel", "Larsen", "DL", "iyyelsec@gmail.com", "4M4Z1N-C0D3", "rootdaniel", 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(9, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Daniel L...");

		// Create Helene
		oprDAO.createOperator(new OperatorDTO(11, "Helene", "Zgaya", "HZ", "heleneyo@gmail.com", "ST4RF4-W4RZ0", "roothelene", 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_ADMIN, 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_FARMACEUT, 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_LABORANT, 0));
		roleDAO.createRole(new RoleDTO(11, textHandler.ROLE_VAERK, 0));
		utils.logMessage("Created operator Helene...");

		utils.logMessage("Done.");
	}

	private static void deleteOperators() throws SQLException {
		utils.logMessage("Deleting all users...");
		connector.getConnection().prepareStatement("DELETE FROM rolle").executeUpdate();
		connector.getConnection().prepareStatement("DELETE FROM operatoer").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void createSampleRaavare() throws SQLException {
		utils.logMessage("Creating sample raavare...");
		connector.getConnection().prepareStatement("INSERT INTO raavare (raavare_id, raavare_navn, leverandoer, status) VALUES (1, 'dej', 'Wawelka', 0), (2, 'tomat', 'Knoor', 0), (3, 'tomat', 'Veaubais', 0), (4, 'tomat', 'Franz', 0), (5, 'ost', 'Ost og Skinke A/S', 0), (6, 'skinke', 'Ost og Skinke A/S', 0), (7, 'champignon', 'Igloo Frostvarer', 0)").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void deleteRaavare() throws SQLException {
		utils.logMessage("Deleting all raavare...");
		connector.getConnection().prepareStatement("DELETE FROM raavare").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void createSampleRaavareBatch() throws SQLException {
		utils.logMessage("Creating sample raavarebatch...");
		connector.getConnection().prepareStatement("INSERT INTO raavarebatch(rb_id, raavare_id, maengde, status) VALUES (1, 1, 1000, 0), (2, 2, 300, 0), (3, 3, 300, 0), (4, 5, 100, 0), (5, 5, 100, 0), (6, 6, 100, 0), (7, 7, 100, 0);").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void deleteRaavareBatch() throws SQLException {
		utils.logMessage("Deleting all raavarebatch...");
		connector.getConnection().prepareStatement("DELETE FROM raavarebatch").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void createSampleRecept() throws SQLException {
		utils.logMessage("Creating sample recept...");
		connector.getConnection().prepareStatement("INSERT INTO recept(recept_id, recept_navn, status) VALUES (1, 'margherita', 0), (2, 'prosciutto', 0), (3, 'capricciosa', 0);").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void deleteRecept() throws SQLException {
		utils.logMessage("Deleting all recept...");
		connector.getConnection().prepareStatement("DELETE FROM recept").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void createSampleReceptKomponent() throws SQLException {
		utils.logMessage("Creating sample receptkomponent...");
		connector.getConnection().prepareStatement("INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance, status) VALUES (1, 1, 10.0, 0.1, 0), (1, 2, 2.0, 0.1, 0), (1, 5, 2.0, 0.1, 0), (2, 1, 10.0, 0.1, 0), (2, 3, 2.0, 0.1, 0), (2, 5, 1.5, 0.1, 0), (2, 6, 1.5, 0.1, 0), (3, 1, 10.0, 0.1, 0), (3, 4, 1.5, 0.1, 0), (3, 5, 1.5, 0.1, 0), (3, 6, 1.0, 0.1, 0), (3, 7, 1.0, 0.1, 0);").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void deleteReceptKomponent() throws SQLException {
		utils.logMessage("Deleting all receptkomponent...");
		connector.getConnection().prepareStatement("DELETE FROM receptkomponent").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void createSampleProduktBatch() throws SQLException {
		utils.logMessage("Creating sample produktbatch...");
		connector.getConnection().prepareStatement("INSERT INTO produktbatch(pb_id, recept_id, item_status, status) VALUES (1, 1, 2, 0), (2, 1, 2, 0), (3, 2, 2, 0), (4, 3, 1, 0), (5, 3, 0, 0);").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void deleteProduktBatch() throws SQLException {
		utils.logMessage("Deleting all produktbatch...");
		connector.getConnection().prepareStatement("DELETE FROM produktbatch").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void createSampleProduktBatchKomponent() throws SQLException {
		utils.logMessage("Creating sample produktbatchkomponentk...");
		connector.getConnection().prepareStatement("INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id, status) VALUES (1, 1, 0.5, 10.05, 1, 0), (1, 2, 0.5, 2.03, 1, 0), (1, 4, 0.5, 1.98, 1, 0), (2, 1, 0.5, 10.01, 2, 0), (2, 2, 0.5, 1.99, 2, 0), (2, 5, 0.5, 1.47, 2, 0), (3, 1, 0.5, 10.07, 1, 0), (3, 3, 0.5, 2.06, 2, 0), (3, 4, 0.5, 1.55, 1, 0), (3, 6, 0.5, 1.53, 2, 0), (4, 1, 0.5, 10.02, 3, 0), (4, 5, 0.5, 1.57, 3, 0), (4, 6, 0.5, 1.03, 3, 0), (4, 7, 0.5, 0.99, 3, 0);").executeUpdate();
		utils.logMessage("Done.");
	}

	private static void deleteProduktBatchKomponent() throws SQLException {
		utils.logMessage("Deleting all produktbatchkomponent...");
		connector.getConnection().prepareStatement("DELETE FROM produktbatchkomponent").executeUpdate();
		utils.logMessage("Done.");
	}

}