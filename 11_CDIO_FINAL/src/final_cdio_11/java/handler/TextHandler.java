package final_cdio_11.java.handler;

import final_cdio_11.java.data.dto.OperatorDTO;

/*
 * Singleton TextHandler class.
 */
public class TextHandler {

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

	/* 
	 * Password characters.
	 */
	public final String PASS_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/* 
	 * DEV_ENABLED messages. 
	 */
	public final String devStartUpMessage = "Application started in [DEV_ENABLED] development mode.";
	public final String devConnectionMessage = "Connected to database: ";
	public final String devConnectionClosedMessage = "Database connection closed.";
	public final String devNewConnectionMessage = "New database connection established.";

	public final String devLoadSqlConfigMessage(String filePath) {
		return "Loaded sql.config file: " + filePath;
	}

	public final String devLoadDbConfigMessage(String filePath) {
		return "Loaded mysql.config file: " + filePath;
	}

	public final String devLoadMailConfigMessage(String filePath) {
		return "Loaded mail.config file: " + filePath;
	}

	public final String devUserDeletedSuccessMessage(int oprId) {
		return "User [" + oprId + "] successfully deleted.";
	}

	public final String devUserDeletedFailureMessage(int oprId) {
		return "User [" + oprId + "] deletion failed.";
	}

	public final String devUserLoginMessage(int oprId, String password) {
		return "User [" + oprId + ":" + password + "] successfully logged in.";
	}

	public final String devMailSentMessage(String to) {
		return "E-mail message sent to '" + to + "'.";
	}

	/* 
	 * Admin role specific.
	 */
	public final String ADMIN_EMAIL = "webadmin@group11.com";

	/* 
	 * Weight specific. 
	 */
	public final String[] WEIGHT_IPS = { "169.254.2.2", "169.254.2.3", "localhost" };
	public final int WEIGHT_PORT = 8000;

	/* 
	 * Application success messages.
	 */
	public final String succLoggedIn = "Logged in successfully.";
	public final String succMailSent = "Mail sent.";

	public final String succAddedUser(int oprId) {
		return "User [" + oprId + "] added successfully.";
	}

	public final String succAddedRole(int oprId, String role) {
		return "Added role '" + role + "' to user [" + oprId + "] successfully.";
	}

	public final String succUpdateRole(int oprId, String role) {
		return "Updated role '" + role + "' to user [" + oprId + "] successfully.";
	}

	public final String succUpdateUser(int oprId) {
		return "User [" + oprId + "] Updated successfully.";
	}

	/* 
	 * Application error messages. 
	 */
	public final String errIdDoesNotExist = "Id does not exist.";
	public final String errIdInvalid = "Invalid Id.";
	public final String errInvalidCredentials = "Invalid credentials.";
	public final String errUnknownFailure = "Failure.";
	public final String errUserCreation = "Failure. Id already exists.";
	public final String errMailFailed = "Mail failed.";

	/*
	 * E-mail specific.
	 */
	public final String mailMessage(OperatorDTO oprDTO) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hej " + oprDTO.getOprFirstName() + " " + oprDTO.getOprLastName() + ".\n\n");
		sb.append("Du har fået et nyt password til hjemmesiden.\n");
		sb.append("Dine nye login oplysninger er: \n");
		sb.append("Id: " + oprDTO.getOprId() + "\n");
		sb.append("Password: " + oprDTO.getOprPassword() + "\n\n");
		sb.append("- Din yndlings Webservice gruppe 11\n");
		return sb.toString();
	}

}