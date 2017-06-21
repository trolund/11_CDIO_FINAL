package final_cdio_11.java.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import final_cdio_11.java.utils.Utils;

public class FileHandler {

	/*
	 * MYSQL_CONFIG_FILE_PATH: Path to the database configuration file. 
	 * SQL_CONFIG_FILE_PATH:   Path to the SQL query configuration file.
	 * MAIL_CONFIG_FILE_PATH:  Path to the Mail configuration file.
	 */
	private final String MYSQL_CONFIG_FILE_PATH = FileHandler.class.getResource("/mysql_oracle.config").getPath();
	private final String SQL_CONFIG_FILE_PATH = FileHandler.class.getResource("/sql.config").getPath();
	private final String MAIL_CONFIG_FILE_PATH = FileHandler.class.getResource("/mail.config").getPath();

	/*
	 * Singleton instance of helper classes.
	 */
	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();

	/*
	 * Map used to store SQL queries and mail properties.
	 */
	private Map<String, String> propMap;

	/*
	 * Singleton instance of FileHandler.
	 */
	private static final FileHandler instance = new FileHandler();

	/*
	 * Class constructor loading configuration files.
	 */
	private FileHandler() {
		propMap = new HashMap<>();
		initConfig();
	}

	/*
	 * Method to return the Singleton instance of this class.
	 */
	public static synchronized FileHandler getInstance() {
		return instance;
	}

	/*
	 * Load all the required configuration files at startup.
	 */
	private void initConfig() {
		loadProperties(MYSQL_CONFIG_FILE_PATH, textHandler.devLoadDbConfigMessage(MYSQL_CONFIG_FILE_PATH));
		loadProperties(SQL_CONFIG_FILE_PATH, textHandler.devLoadSqlConfigMessage(SQL_CONFIG_FILE_PATH));
		loadProperties(MAIL_CONFIG_FILE_PATH, textHandler.devLoadMailConfigMessage(MAIL_CONFIG_FILE_PATH));
	}

	/*
	 * Method to load the SQL query configuration file and put all of the information into a HashMap.
	 */
	private void loadProperties(String filePath, String message) {
		Properties p = new Properties();

		if (utils.DEV_ENABLED) utils.logMessage(message);

		try (InputStream is = new FileInputStream(filePath)) {
			p.load(is);
			for (String key : p.stringPropertyNames()) {
				String value = p.getProperty(key);
				propMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Method to get the desired property at the key.
	 */
	public String getProperty(String key) {
		return propMap.get(key);
	}

}