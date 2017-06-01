package final_cdio_11.java.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	 * MYSQL_CONFIG_FILE: Path to the database configuration file. 
	 * SQL_CONFIG_FILE:   Path to the SQL query configuration file.
	 */
	private final URL MYSQL_CONFIG_FILE = FileHandler.class.getResource("/mysql_oracle.config");
	private final URL SQL_CONFIG_FILE = FileHandler.class.getResource("/sql.config");
	private final URL Mail_CONFIG_FILE = FileHandler.class.getResource("/mail.config");

	private static final FileHandler instance = new FileHandler();

	private final Utils utils;
	private final TextHandler textHandler;

	private Map<String, String> sqlHashMap;
	private Map<String, String> mailHashMap;

	private FileHandler() {
		utils = Utils.getInstance();
		textHandler = TextHandler.getInstance();
		sqlHashMap = new HashMap<>();
		mailHashMap = new HashMap<>();

		loadDatabaseProperties();
		loadSQLProperties();
		loadMailProperties();
	}

	/*
	 * Method to load the database configuration from file into Java properties.
	 */
	private void loadDatabaseProperties() {
		Properties p = new Properties();
		String path = MYSQL_CONFIG_FILE.getPath();

		if (utils.DEV_ENABLED) utils.logMessage(textHandler.devLoadDatabaseConfigMessage + path);

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

		if (utils.DEV_ENABLED) utils.logMessage(textHandler.devLoadingSqlConfigMessage + path);

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
	
	
	public String getMailProperty(String key) {
		return mailHashMap.get(key);
	}

	/*
	 * Method to return the Singleton instance of this class.
	 */
	public static synchronized FileHandler getInstance() {
		return instance;
	}
	
	private void loadMailProperties() {
		Properties p = new Properties();
		String path = Mail_CONFIG_FILE.getPath();

		if (utils.DEV_ENABLED) utils.logMessage("mail?" + path);

		try (InputStream is = new FileInputStream(path)) {
			p.load(is);
			for (String key : p.stringPropertyNames()) {
				String value = p.getProperty(key);
				mailHashMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}