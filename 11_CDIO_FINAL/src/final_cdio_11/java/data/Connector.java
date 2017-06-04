package final_cdio_11.java.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import final_cdio_11.java.handler.FileHandler;
import final_cdio_11.java.handler.TextHandler;
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

	/* Class object fields. */
	private Utils utils = Utils.getInstance();
	private TextHandler textHandler = TextHandler.getInstance();
	private FileHandler fileHandler = FileHandler.getInstance();

	/* Singleton instance of this class. */
	private static final Connector instance = new Connector();

	/*
	 * Database connection object and HashMap object to store all SQL queries used by this application.
	 */
	private Connection connection;

	/*
	 * Constructor that loads all of the configuration files at startup and creates a database connection immediately.
	 */
	private Connector() {
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
	 * Method to return the Singleton instance of this class.
	 */
	public static synchronized Connector getInstance() {
		return instance;
	}

	/*
	 * Setup the database connection properties.
	 */
	private void setupConnectionProperties() {
		this.driverClass = fileHandler.getProperty("DRIVER_CLASS");
		this.driver = fileHandler.getProperty("DRIVER");
		this.host = fileHandler.getProperty("HOST");
		this.port = Integer.parseInt(fileHandler.getProperty("PORT"));
		this.database = fileHandler.getProperty("DATABASE");
		this.username = fileHandler.getProperty("USERNAME");
		this.password = fileHandler.getProperty("PASSWORD");
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
	 * Create new database connection object and return it.
	 */
	private Connection createConnection() throws SQLException {
		Connection newConnection = DriverManager.getConnection(url, username, password);
		return newConnection;
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

	/*
	 * Return loaded SQL query from FileHandler.
	 */
	public String getQuery(String key) {
		return fileHandler.getProperty(key);
	}

}