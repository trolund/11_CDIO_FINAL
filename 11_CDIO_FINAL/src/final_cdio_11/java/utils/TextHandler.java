package final_cdio_11.java.utils;

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

	/* password characters */
	public final String PASS_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

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

	public final String succAddedRole(int oprId, String role) {
		return "Added role '" + role + "' to user [" + oprId + "] successfully.";
	}
	
	public final String succUpdateRole(int oprId, String role) {
		return "Updated role '" + role + "' to user [" + oprId + "] successfully.";
	}
	
	public final String succUpdateUser(int oprId) {
		return "User [" + oprId + "] Updated successfully.";
	}

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

	/* Application error messages. */
	public final String errIdDoesNotExist = "Id does not exist.";
	public final String errIdInvalid = "Invalid Id.";
	public final String errInvalidCredentials = "Invalid credentials.";
	public final String errUnknownFailure = "Failure.";
	public final String errUserCreation = "Failure. Id already exists.";

	/*
	 * Roles.
	 */
	public final String ROLE_ADMIN = "Admin";
	public final String ROLE_FARMACEUT = "Farmaceut";
	public final String ROLE_VAERK = "Værkfører";
	public final String ROLE_LABORANT = "Laborant";

}