package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
 * Singleton database connector class.
 * 
 * This class has the responsibility to load database information and SQL queries 
 * at start up as well as to maintain them and update them accordingly.
 */
public class Connector {

	/*
	 * MYSQL_CONFIG_FILE: Path to the database configuration file.
	 * SQL_CONFIG_FILE:   Path to the SQL query configuration file.
	 */
	private final String MYSQL_CONFIG_FILE = "mysql_mariadb.config";
	private final String SQL_CONFIG_FILE = "sql.config";

	/*
	 * String objects to hold the database configuration information.
	 */
	private String driverClass;
	private String driver;
	private String host;
	private int port;
	private String database;
	private String username;
	private String password;

	/*
	 * Database connection object and HashMap object to store all 
	 * SQL queries used by this application.
	 */
	private Connection connection;
	private Map<String, String> sqlHashMap;

	/*
	 * Singleton instance of this class.
	 */
	private static final Connector instance = new Connector();

	/*
	 * Constructor that loads all of the configuration files at startup
	 * and creates a database connection immediately.
	 */
	private Connector() {
		sqlHashMap = new HashMap<>();
		loadConnectorProperties();
		/* 
		 * It's not necessary to keep creating a new SQL property file.
		 * Uncomment if the SQL properties needs to get updated.
		 */
		//createSQLProperties();
		loadSQLProperties();

		try {
			Class.forName(driverClass);
			String url = driver + "://" + host + ":" + port + "/" + database;
			connection = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("DEBUG: [DBConnection] Status: Successfully connected to MySQL database,\nhost '" + host + "' with user '" + username + "'.");
	}

	/*
	 * Method to load the database configuration from file into Java properties.
	 */
	private void loadConnectorProperties() {
		Properties p = new Properties();
		try (InputStream is = new FileInputStream(MYSQL_CONFIG_FILE)) {
			p.load(is);
			this.driverClass = p.getProperty("DRIVER_CLASS");
			this.driver = p.getProperty("DRIVER");
			this.host = p.getProperty("HOST");
			this.port = Integer.parseInt(p.getProperty("PORT"));
			this.database = p.getProperty("DATABASE");
			this.username = p.getProperty("USERNAME");
			this.password = p.getProperty("PASSWORD");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		p.setProperty("createOprSql", "INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password) VALUES (?, ?, ?, ?, ?)");
		p.setProperty("updateOprSql", "UPDATE operatoer SET opr_navn = ?, ini = ?, cpr = ?, password = ? WHERE opr_id = ?");
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
		p.setProperty("createRCSql", "INSERT INTO receptkomponent(recept_id, raavare_id, nom_netto, tolerance) VALUES (?, ?, ?, ?)");
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

		/* Saves and outputs the file. */
		FileOutputStream fs = null;
		try {
			File file = new File(SQL_CONFIG_FILE);
			fs = new FileOutputStream(file);
			p.store(fs, SQL_CONFIG_FILE);
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
	 * Method to load the SQL query configuration file
	 * and put all of the information into a HashMap.
	 */
	private void loadSQLProperties() {
		Properties p = new Properties();
		try (InputStream is = new FileInputStream(SQL_CONFIG_FILE)) {
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
	 * Method to close a desired PreparedStatement
	 * and ResultSet.
	 */
	public void cleanup(PreparedStatement stmt, ResultSet rs) throws SQLException {
		if (stmt != null) stmt.close();
		if (rs != null) rs.close();
	}

	/*
	 * Method to close a desired PreparedStatement.
	 */
	public void cleanup(PreparedStatement stmt) throws SQLException {
		if (stmt != null) stmt.close();
	}

	/*
	 * Method to close the database connection.
	 */
	public void closeConnection() throws SQLException {
		connection.close();
	}

	/*
	 * Method to get the desired SQL query at the key.
	 */
	public String getSQL(String key) {
		return sqlHashMap.get(key);
	}

	/*
	 * Method to get the database connection object.
	 */
	public Connection getConnection() {
		return connection;
	}

	/*
	 * Method to return the Singleton instance of this class.
	 */
	public static synchronized Connector getInstance() {
		return instance;
	}

}