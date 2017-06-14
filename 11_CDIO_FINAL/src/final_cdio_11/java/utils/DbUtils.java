package final_cdio_11.java.utils;

import java.sql.SQLException;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;

/*
 * Utility class for resetting the database back with some demonstration data.
 * resetDatabase method is called, the database is totally wiped and then
 * restored with demonstration data.
 */
public class DbUtils {

	private static final Connector connector = Connector.getInstance();
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
		if (utils.DEV_ENABLED) utils.logMessage("WARNING: Database is getting reset!");
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
		if (utils.DEV_ENABLED) utils.logMessage("WARNING: Database was reset successfully!");
	}

	private static void createSampleOperators() throws DALException {
		IOperatorDAO oprDAO = new SQLOperatorDAO(connector);
		IRoleDAO roleDAO = new SQLRoleDAO(connector);

		if (utils.DEV_ENABLED) utils.logMessage("Creating sample operators...");

		// Create Admin
		oprDAO.createOperator(new OperatorDTO(10, "Admin", "Admin", "ADM", "webadmin@group11.com", "2412997890", "root", 0));
		roleDAO.createRole(new RoleDTO(10, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(10, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(10, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(10, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Admin...");

		// Create Stig 
		oprDAO.createOperator(new OperatorDTO(1, "Stig", "Høgh", "SH", "shg@dtu.dk", "0911974321", "rootstig", 0));
		roleDAO.createRole(new RoleDTO(1, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(1, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(1, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Stig...");

		// Create Finn
		oprDAO.createOperator(new OperatorDTO(2, "Finn", "Gustafsson", "FG", "fiu@dtu.dk", "1302924680", "rootfinn", 0));
		roleDAO.createRole(new RoleDTO(2, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(2, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Finn...");

		// Create Ronnie
		oprDAO.createOperator(new OperatorDTO(3, "Ronnie", "Dalsgaard", "RD", "ronniedalsgaard@gmail.com", "1103866547", "rootronnie", 0));
		roleDAO.createRole(new RoleDTO(3, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Ronnie...");

		// Create Christian
		oprDAO.createOperator(new OperatorDTO(4, "Christian", "Budtz", "CB", "s136400@student.dtu.dk", "2902915432", "rootchristian", 0));
		roleDAO.createRole(new RoleDTO(4, Role.Farmaceut.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Christian...");

		// Create Troels
		oprDAO.createOperator(new OperatorDTO(5, "Troels", "Lund", "TL", "trolund@gmail.com", "0808884793", "roottroels", 0));
		roleDAO.createRole(new RoleDTO(5, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(5, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(5, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(5, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Troels...");

		// Create Thomas
		oprDAO.createOperator(new OperatorDTO(6, "Thomas", "Mascagni", "TM", "thomes@gmail.com", "1111118764", "rootthomas", 0));
		roleDAO.createRole(new RoleDTO(6, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(6, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(6, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(6, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Thomas...");

		// Create Behnia
		oprDAO.createOperator(new OperatorDTO(7, "Behnia", "Farazan", "BF", "gunit@gmail.com", "0101015654", "rootbehnia", 0));
		roleDAO.createRole(new RoleDTO(7, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(7, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(7, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(7, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Behnia...");

		// Create Daniel A.
		oprDAO.createOperator(new OperatorDTO(8, "Daniel", "Anusic", "DA", "dannyboi@gmail.com", "0203049483", "rootdaniel", 0));
		roleDAO.createRole(new RoleDTO(8, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(8, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(8, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(8, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Daniel A...");

		// Create Daniel L.
		oprDAO.createOperator(new OperatorDTO(9, "Daniel", "Larsen", "DL", "iyyelsec@gmail.com", "0908076543", "rootdaniel", 0));
		roleDAO.createRole(new RoleDTO(9, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(9, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(9, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(9, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Daniel L...");

		// Create Helene
		oprDAO.createOperator(new OperatorDTO(11, "Helene", "Zgaya", "HZ", "heleneyo@gmail.com", "1011123456", "roothelene", 0));
		roleDAO.createRole(new RoleDTO(11, Role.Admin.toString(), 0));
		roleDAO.createRole(new RoleDTO(11, Role.Farmaceut.toString(), 0));
		roleDAO.createRole(new RoleDTO(11, Role.Laborant.toString(), 0));
		roleDAO.createRole(new RoleDTO(11, Role.Værkfører.toString(), 0));
		if (utils.DEV_ENABLED) utils.logMessage("Created operator Helene...");

		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteOperators() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all users...");
		connector.getConnection().prepareStatement("DELETE FROM rolle").executeUpdate();
		connector.getConnection().prepareStatement("DELETE FROM operatoer").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void createSampleRaavare() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Creating sample raavare...");
		connector.getConnection().prepareStatement("INSERT INTO raavare (raavare_id, raavare_navn, leverandoer, status) VALUES (1, 'Salt', 'SaltCO', 0), (2, 'Citron', 'Citraz', 0), (3, 'Vand', 'WaterCompany', 0), (4, 'Salt', 'Saltastic', 0), (5, 'Ingefær', 'MedHerbs', 0), (6, 'Eddike', 'Syre A/S', 0), (7, 'Klor', 'Syre A/S', 0)").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteRaavare() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all raavare...");
		connector.getConnection().prepareStatement("DELETE FROM raavare").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void createSampleRaavareBatch() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Creating sample raavarebatch...");
		connector.getConnection().prepareStatement("INSERT INTO raavarebatch(rb_id, raavare_id, maengde, status) VALUES (1, 1, 1000, 0), (2, 2, 300, 0), (3, 3, 300, 0), (4, 5, 100, 0), (5, 5, 100, 0), (6, 6, 100, 0), (7, 7, 100, 0);").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteRaavareBatch() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all raavarebatch...");
		connector.getConnection().prepareStatement("DELETE FROM raavarebatch").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void createSampleRecept() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Creating sample recept...");
		connector.getConnection().prepareStatement("INSERT INTO recept(recept_id, recept_navn, status) VALUES (1, 'Adderall', 0), (2, 'Paracetamol', 0), (3, 'Prozac', 0);").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteRecept() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all recept...");
		connector.getConnection().prepareStatement("DELETE FROM recept").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void createSampleReceptKomponent() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Creating sample receptkomponent...");
		connector.getConnection().prepareStatement("INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance, status) VALUES (1, 1, 0.3, 0.1, 0), (1, 2, 0.3, 0.1, 0), (1, 5, 0.3, 0.1, 0), (2, 1, 0.3, 0.1, 0), (2, 3, 0.5, 0.1, 0), (2, 5, 0.4, 0.1, 0), (2, 6, 0.4, 0.1, 0), (3, 1, 0.2, 0.1, 0), (3, 4, 0.6, 0.1, 0), (3, 5, 0.2, 0.1, 0), (3, 6, 0.2, 0.1, 0), (3, 7, 0.4, 0.1, 0);").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteReceptKomponent() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all receptkomponent...");
		connector.getConnection().prepareStatement("DELETE FROM receptkomponent").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void createSampleProduktBatch() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Creating sample produktbatch...");
		connector.getConnection().prepareStatement("INSERT INTO produktbatch(pb_id, recept_id, item_status, status) VALUES (1, 1, 2, 0), (2, 1, 2, 0), (3, 2, 2, 0), (4, 3, 1, 0), (5, 3, 0, 0);").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteProduktBatch() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all produktbatch...");
		connector.getConnection().prepareStatement("DELETE FROM produktbatch").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void createSampleProduktBatchKomponent() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Creating sample produktbatchkomponentk...");
		connector.getConnection().prepareStatement("INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id, status) VALUES (1, 1, 0.5, 10.05, 1, 0), (1, 2, 0.5, 2.03, 1, 0), (1, 4, 0.5, 1.98, 1, 0), (2, 1, 0.5, 10.01, 2, 0), (2, 2, 0.5, 1.99, 2, 0), (2, 5, 0.5, 1.47, 2, 0), (3, 1, 0.5, 10.07, 1, 0), (3, 3, 0.5, 2.06, 2, 0), (3, 4, 0.5, 1.55, 1, 0), (3, 6, 0.5, 1.53, 2, 0), (4, 1, 0.5, 10.02, 3, 0), (4, 5, 0.5, 1.57, 3, 0), (4, 6, 0.5, 1.03, 3, 0), (4, 7, 0.5, 0.99, 3, 0);").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

	private static void deleteProduktBatchKomponent() throws SQLException {
		if (utils.DEV_ENABLED) utils.logMessage("Deleting all produktbatchkomponent...");
		connector.getConnection().prepareStatement("DELETE FROM produktbatchkomponent").executeUpdate();
		if (utils.DEV_ENABLED) utils.logMessage("Done.");
	}

}