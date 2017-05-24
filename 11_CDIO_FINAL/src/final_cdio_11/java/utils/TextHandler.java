package final_cdio_11.java.utils;

public class TextHandler {

	private static final TextHandler instance = new TextHandler();

	/* DEV_ENABLED messages */
	public final String devStartUpMessage = "Application started in [DEV_ENABLED] development mode.";
	public final String devConnectionMessage = "Connected to database: ";
	public final String devConnectionClosedMessage = "Database connection closed.";
	public final String devNewConnectionMessage = "New database connection established.";
	public final String devSavingSqlConfigMessage = "Saving sql.config file: ";
	public final String devLoadingSqlConfigMessage = "Loading sql.config file: ";
	public final String devLoadDatabaseConfigMessage = "Loading mysql.config file: ";

	/* TEST_ENABLED messages */
	public final String testStartUpMessage = "Application started in [TEST_ENABLED] test mode.";

	private TextHandler() {

	}

	public synchronized static TextHandler getInstance() {
		return instance;
	}

}