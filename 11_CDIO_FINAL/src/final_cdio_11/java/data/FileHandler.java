package final_cdio_11.java.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;

public class FileHandler {

	/*
	 * MYSQL_CONFIG_FILE: Path to the database configuration file. SQL_CONFIG_FILE: Path to the SQL query configuration file.
	 */
	private final URL MYSQL_CONFIG_FILE = FileHandler.class.getResource("/mysql_oracle.config");
	private final URL SQL_CONFIG_FILE = FileHandler.class.getResource("/sql.config");

	private static final FileHandler instance = new FileHandler();

	private final Utils utils;
	private final TextHandler textHandler;

	private Map<String, String> sqlHashMap;

	private FileHandler() {
		utils = Utils.getInstance();
		textHandler = TextHandler.getInstance();
		sqlHashMap = new HashMap<>();

		// createSQLProperties();
		loadDatabaseProperties();
		loadSQLProperties();
	}

	/*
	 * Method to create the SQL query configuration file.
	 */
	@SuppressWarnings("unused")
	private void createSQLProperties() {
		Properties p = new Properties();

		/* Operator SQL */
		p.setProperty("getOprSql", "SELECT * FROM operatoer WHERE opr_id = ?");
		p.setProperty("getOprListSql", "SELECT * FROM operatoer");
		p.setProperty("createOprSql", "CALL CREATE_OPERATOR(?, ?, ?, ?, ?)");
		p.setProperty("updateOprSql", "UPDATE operatoer SET opr_navn = ?, ini = ?, cpr = ?, password = ? WHERE opr_id = ?");
		p.setProperty("deleteOprRolesSql", "CALL DELETE_OPR_ROLES(?)");
		p.setProperty("deleteOprSql", "DELETE FROM operatoer WHERE opr_id = ?");

		/* ProductBatchComponent SQL */
		p.setProperty("getPBCSql", "SELECT * FROM produktbatchkomponent WHERE pb_id = ? AND rb_id = ?");
		p.setProperty("getPBCListIdSql", "SELECT * FROM produktbatchkomponent WHERE pb_id = ?");
		p.setProperty("getPBCListSql", "SELECT * FROM produktbatchkomponent");
		p.setProperty("createPBCSql", "INSERT INTO produktbatchkomponent(pb_id, rb_id, tara, netto, opr_id) VALUES (?, ?, ?, ?, ?)");
		p.setProperty("updatePBCSql", "UPDATE produktbatchkomponent SET tara = ?, netto = ? WHERE pb_id = ? AND rb_id = ? AND opr_id = ?");
		p.setProperty("deletePBCSql", "DELETE FROM produktbatchkomponent WHERE pb_id = ? AND rb_id = ?");

		/* ProductBatch SQL */
		p.setProperty("getPBSql", "SELECT * FROM produktbatch WHERE pb_id = ?");
		p.setProperty("getPBListSql", "SELECT * FROM produktbatch");
		p.setProperty("createPBSql", "INSERT INTO produktbatch(pb_id, status, recept_id) VALUES (?, ?, ?)");
		p.setProperty("updatePBSql", "UPDATE produktbatch SET status = ? WHERE pb_id = ? AND recept_id = ?");
		p.setProperty("deletePBSql", "DELETE FROM produktbatch WHERE pb_id = ?");

		/* RaavareBatch SQL */
		p.setProperty("getRBSql", "SELECT * FROM raavarebatch WHERE rb_id = ?");
		p.setProperty("getRBListSql", "SELECT * FROM raavarebatch");
		p.setProperty("getRBListIdSql", "SELECT * FROM raavarebatch WHERE rb_id = ?");
		p.setProperty("createRBSql", "INSERT INTO raavarebatch(rb_id, raavare_id, maengde) VALUES (?, ?, ?)");
		p.setProperty("updateRBSql", "UPDATE raavarebatch SET maengde = ? WHERE rb_id = ? AND raavare_id = ?");
		p.setProperty("deleteRBSql", "DELETE FROM raavarebatch WHERE rb_id = ?");

		/* Raavare SQL */
		p.setProperty("getRaavareSql", "SELECT * FROM raavare WHERE raavare_id = ?");
		p.setProperty("getRaavareListSql", "SELECT * FROM raavare");
		p.setProperty("createRaavareSql", "INSERT INTO raavare(raavare_id, raavare_navn, leverandoer) VALUES (?, ?, ?)");
		p.setProperty("updateRaavareSql", "UPDATE raavare SET raavare_navn = ?, leverandoer = ? WHERE raavare_id = ?");
		p.setProperty("deleteRaavareSql", "DELETE FROM raavare WHERE raavare_id = ?");

		/* ReceptComponent SQL */
		p.setProperty("getRCSql", "SELECT * FROM receptkomponent WHERE recept_id = ? AND raavare_id = ?");
		p.setProperty("getRCListIdSql", "SELECT * FROM receptkomponent WHERE recept_id = ?");
		p.setProperty("getRCListSql", "SELECT * FROM receptkomponent");
		p.setProperty("createRCSql", "CALL CREATE_RECEPTKOMPONENT(?, ?, ?, ?)");
		p.setProperty("updateRCSql", "UPDATE receptkomponent SET nom_netto = ?, tolerance = ? WHERE recept_id = ? AND raavare_id = ?");
		p.setProperty("deleteRCSql", "DELETE FROM receptkomponent WHERE recept_id = ? AND raavare_id = ?");

		/* Recept SQL */
		p.setProperty("getReceptSql", "SELECT * FROM recept WHERE recept_id = ?");
		p.setProperty("getReceptListSql", "SELECT * FROM recept");
		p.setProperty("createReceptSql", "INSERT INTO recept(recept_id, recept_navn) VALUES (?, ?)");
		p.setProperty("updateReceptSql", "UPDATE recept SET recept_navn = ? WHERE recept_id = ?");
		p.setProperty("deleteReceptSql", "DELETE FROM recept WHERE recept_id = ?");

		/* Role SQL */
		p.setProperty("getOprRolesSql", "SELECT * FROM rolle WHERE opr_id = ?");
		p.setProperty("getRoleListSql", "SELECT * FROM rolle");
		p.setProperty("createRoleSql", "INSERT INTO rolle(opr_id, rolle_navn) VALUES (?, ?)");
		p.setProperty("deleteRoleSql", "DELETE FROM rolle WHERE opr_id = ? AND rolle_navn = ?");

		/* AdminForemanPBC View SQL */
		p.setProperty("getVAdminForemanPBCSql", "SELECT * FROM v_admin_foreman_pbc WHERE opr_id = ?");
		p.setProperty("getVAdminForemanPBCListSql", "SELECT * FROM v_admin_foreman_pbc");

		/* AdminOperator View SQL */
		p.setProperty("getVAdminOperatorSql", "SELECT * FROM v_admin_operator WHERE opr_id = ?");
		p.setProperty("getVAdminOperatorListSql", "SELECT * FROM v_admin_operator");

		/* ForemanOperator View SQL */
		p.setProperty("getVForemanOperatorSql", "SELECT * FROM v_foreman_operator WHERE opr_id = ?");
		p.setProperty("getVForemanOperatorListSql", "SELECT * FROM v_foreman_operator");

		/* OperatorRB View SQL */
		p.setProperty("getVOperatorRBSql", "SELECT * FROM v_operator_rb WHERE raavare_id = ?");
		p.setProperty("getVOperatorRBListSql", "SELECT * FROM v_operator_rb");

		/* OperatorRecipe View SQL */
		p.setProperty("getVOperatorRecipeSql", "SELECT * FROM v_operator_recipe WHERE recept_id = ?");
		p.setProperty("getVOperatorRecipeListSql", "SELECT * FROM v_operator_recipe");

		/* PharmacistRecipe View SQL */
		p.setProperty("getVPharmacistRecipeSql", "SELECT * FROM v_pharmacist_recipe WHERE recept_id = ?");
		p.setProperty("getVPharmacistRecipeListSql", "SELECT * FROM v_pharmacist_recipe");

		/* Saves and outputs the file. */
		FileOutputStream fs = null;
		try {
			String path = SQL_CONFIG_FILE.getPath();

			if (utils.DEV_ENABLED) {
				utils.logMessage(textHandler.devSavingSqlConfigMessage + path);
			}

			File file = new File(SQL_CONFIG_FILE.getPath());
			fs = new FileOutputStream(file);
			p.store(fs, SQL_CONFIG_FILE.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Method to load the database configuration from file into Java properties.
	 */
	private void loadDatabaseProperties() {
		Properties p = new Properties();
		String path = MYSQL_CONFIG_FILE.getPath();

		if (utils.DEV_ENABLED) {
			utils.logMessage(textHandler.devLoadDatabaseConfigMessage + path);
		}

		try (InputStream is = new FileInputStream(path)) {
			p.load(is);
			for (String key : p.stringPropertyNames()) {
				String value = p.getProperty(key);
				sqlHashMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to load the SQL query configuration file and put all of the information into a HashMap.
	 */
	private void loadSQLProperties() {
		Properties p = new Properties();
		String path = SQL_CONFIG_FILE.getPath();

		if (utils.DEV_ENABLED) {
			utils.logMessage(textHandler.devLoadingSqlConfigMessage + path);
		}

		try (InputStream is = new FileInputStream(path)) {
			p.load(is);
			for (String key : p.stringPropertyNames()) {
				String value = p.getProperty(key);
				sqlHashMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to get the desired SQL query at the key.
	 */
	public String getSQL(String key) {
		return sqlHashMap.get(key);
	}

	/*
	 * Method to return the Singleton instance of this class.
	 */
	public static synchronized FileHandler getInstance() {
		return instance;
	}

}