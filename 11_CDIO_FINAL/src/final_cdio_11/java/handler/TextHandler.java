package final_cdio_11.java.handler;

import final_cdio_11.java.data.dto.OperatorDTO;

/*
 * Singleton TextHandler class.
 * This class contains all the used text, ports, etc.
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

	public final String devOprDeletedSuccessMessage(int oprId) {
		return "Operator[" + oprId + "] successfully deleted.";
	}

	public final String devOprDeletedFailureMessage(int oprId) {
		return "Operator [" + oprId + "] deletion failed.";
	}

	public final String succLoginMessage(int oprId, String password) {
		return "Operator [" + oprId + ":" + password + "] successfully logged in.";
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

	public final String attemptOprUpdate(OperatorDTO oprDTO) {
		return "Opdating operator: " + oprDTO;
	}

	/* 
	 * Application success messages.
	 */
	public final String succLoggedIn = "Logged in successfully.";
	public final String succMailSent = "Mail sent successfully.";

	public final String succOprAdd(int oprId) {
		return "Operator [" + oprId + "] added successfully.";
	}

	public final String succRoleAdd(int oprId, String role) {
		return "Added role '" + role + "' to operator [" + oprId + "] successfully.";
	}

	public final String succUpdateRole(int oprId, String role) {
		return "Updated role '" + role + "' for operator [" + oprId + "] successfully.";
	}

	public final String succOprUpdate(int oprId) {
		return "Operator [" + oprId + "] updated successfully.";
	}

	/* Operator success messages */
	public final String succOprCreate = "Success: Operator create.";
	public final String succOprUpdate = "Success: Operator update.";
	public final String succOprDelete = "Success: Operator delete.";

	/* Product Batch success messages */
	public final String succPbCreate = "Success: Product Batch create.";
	public final String succPbUpdate = "Success: Product Batch update.";
	public final String succPbDelete = "Success: Product Batch delete.";

	/* Raavare Batch success messages */
	public final String succRbCreate = "Success: Material Batch create.";
	public final String succRbUpdate = "Success: Material Batch update.";
	public final String succRbDelete = "Success: Material Batch delete.";

	/* Raavare success messages */
	public final String succRaavareCreate = "Success: Material create.";
	public final String succRaavareUpdate = "Success: Material update.";
	public final String succRaavareDelete = "Success: Material delete.";

	/* Receipt success messages */
	public final String succReceiptCreate = "Success: Receipt create.";
	public final String succReceiptUpdate = "Success: Receipt update.";
	public final String succReceiptDelete = "Success: Receipt delete.";

	/* 
	 * Application error messages. 
	 */
	public final String errIdDoesNotExist = "Id does not exist.";
	public final String errIdInvalid = "Invalid Id.";
	public final String errInvalidCredentials = "Invalid credentials.";
	public final String errUnknownFailure = "Failure.";
	public final String errOprCreation = "Failure. Id already exists.";
	public final String errMailFailed = "Mail failed.";

	public final String errLoginFailed(int oprId) {
		return "Operator [" + oprId + "] failed to log in.";
	}

	/* Operator error messages */
	public final String errOprCreate = "Failed: Operator create.";
	public final String errOprUpdate = "Failed: Operator update.";
	public final String errOprDelete = "Failed: Operator delete.";
	public final String errOprInvalid = "Failed: Operator contains invalid fields.";

	/* Product Batch error messages */
	public final String errPbCreate = "Failed: Product batch create.";
	public final String errPbUpdate = "Failed: Product Batch update.";
	public final String errPbDelete = "Failed: Product Batch delete.";
	public final String errPbInvalid = "Failed: Product batch contains invalid fields.";

	/* Raavare Batch error messages */
	public final String errRbCreate = "Failed: Material Batch create.";
	public final String errRbUpdate = "Failed: Material Batch update.";
	public final String errRbDelete = "Failed: Material Batch delete.";
	public final String errRbInvalid = "Failed: Material Batch contains invalid fields.";

	/* Raavare error messages */
	public final String errRaavareCreate = "Failed: Material create.";
	public final String errRaavareUpdate = "Failed: Material update.";
	public final String errRaavareDelete = "Failed: Material delete.";
	public final String errRaavareInvalid = "Failed: Material contains invalid fields.";

	/* Receipt error messages */
	public final String errReceiptCreate = "Failure: Receipt create.";
	public final String errReceiptUpdate = "Failure: Receipt update.";
	public final String errReceiptDelete = "Failure: Receipt delete.";
	public final String errReceiptInvalid = "Failure: Receipt contains invalid fields.";

	/*
	 * E-mail specific.
	 */
	public final String mailNewPassSubject = "11_CDIO_FINAL: New website password";

	public final String mailMessage(OperatorDTO oprDTO, String newPass) {
		StringBuilder sb = new StringBuilder();
		sb.append("Greetings " + oprDTO.getOprFirstName() + " " + oprDTO.getOprLastName() + ".\n\n");
		sb.append("You have received a new password for the website.\n\n");
		sb.append("Your new login credentials can be seen below: \n\n");
		sb.append("Id: " + oprDTO.getOprId() + "\n");
		sb.append("Password: " + newPass + "\n\n");
		sb.append("Sincerely,\n");
		sb.append("Your favorite web service provider, group 11.\n");
		return sb.toString();
	}

}