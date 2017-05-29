package final_cdio_11.java.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;

/*
 * Singleton database connector class.
 * 
 * This class has the responsibility to load database information and SQL queries 
 * at start up as well as to maintain them and update them accordingly.
 */
public class Connector {

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
	private String url;

	/* Singleton instance of this class. */
	private static final Connector instance = new Connector();

	/* Class object fields. */
	private Utils utils;
	private TextHandler textHandler;
	private FileHandler fileHandler;

	/*
	 * Database connection object and HashMap object to store all SQL queries used by this application.
	 */
	private Connection connection;

	/*
	 * Constructor that loads all of the configuration files at startup and creates a database connection immediately.
	 */
	private Connector() {
		utils = Utils.getInstance();
		textHandler = TextHandler.getInstance();
		fileHandler = FileHandler.getInstance();

		setupConnectionProperties();

		try {
			Class.forName(driverClass);
			url = driver + "://" + host + ":" + port + "/" + database + "?verifyServerCertificate=false&useSSL=true";
			connection = DriverManager.getConnection(url, username, password);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devConnectionMessage + url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.exit(1);
		}
	}

	/*
	 * Method to close a desired PreparedStatement and ResultSet.
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
	 * Method to get the database connection object.
	 */
	public Connection getConnection() {
		try {
			if (!connection.isValid(1)) connection = createConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Temporary method
	public String getQuery(String key) {
		return fileHandler.getSQL(key);
	}

	private void setupConnectionProperties() {
		this.driverClass = fileHandler.getSQL("DRIVER_CLASS");
		this.driver = fileHandler.getSQL("DRIVER");
		this.host = fileHandler.getSQL("HOST");
		this.port = Integer.parseInt(fileHandler.getSQL("PORT"));
		this.database = fileHandler.getSQL("DATABASE");
		this.username = fileHandler.getSQL("USERNAME");
		this.password = fileHandler.getSQL("PASSWORD");
	}

	private Connection createConnection() throws SQLException {
		Connection newConnection = DriverManager.getConnection(url, username, password);
		return newConnection;
	}

	/*
	 * Method to return the Singleton instance of this class.
	 */
	public static synchronized Connector getInstance() {
		return instance;
	}

}