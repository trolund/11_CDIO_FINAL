package final_cdio_11.java.utils;

/*
 * Singleton TextHandler class.
 */
public class TextHandler {

	/* DEV_ENABLED messages. */
	public final String devStartUpMessage = "Application started in [DEV_ENABLED] development mode.";
	public final String devConnectionMessage = "Connected to database: ";
	public final String devConnectionClosedMessage = "Database connection closed.";
	public final String devNewConnectionMessage = "New database connection established.";
	public final String devSavingSqlConfigMessage = "Saving sql.config file: ";
	public final String devLoadingSqlConfigMessage = "Loading sql.config file: ";
	public final String devLoadDatabaseConfigMessage = "Loading mysql.config file: ";

	public final String devUserDeletedSuccessMessage(int oprId) {
		return "User [" + oprId + "] successfully deleted.";
	}

	public final String devUserDeletedFailureMessage(int oprId) {
		return "User [" + oprId + "] deletion failed.";
	}

	public final String devUserLoginMessage(int oprId, String password) {
		return "User [" + oprId + ":" + password + "] successfully logged in.";
	}

	/* TEST_ENABLED messages. */
	public final String testStartUpMessage = "Application started in [TEST_ENABLED] test mode.";

	/* Application success messages. */
	public final String succLoggedIn = "Logged in successfully.";

	public final String succAddedUser(int oprId) {
		return "User [" + oprId + "] added successfully.";
	}

	/* Application error messages. */
	public final String errIdDoesNotExist = "Id does not exist.";
	public final String errIdInvalid = "Invalid Id.";
	public final String errInvalidCredentials = "Invalid credentials.";
	public final String errFailure = "Failure.";

	private static final TextHandler instance = new TextHandler();

	/*
	 * Private constructor. Can't be instantiated.
	 */
	private TextHandler() {

	}

	/*
	 * Return method for the Singleton instance.
	 */
	public synchronized static TextHandler getInstance() {
		return instance;
	}

}