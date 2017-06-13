package final_cdio_11.java.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;

import final_cdio_11.java.handler.TextHandler;

/*
 * Singleton class for various utilities.
 */
public class Utils {

	/*
	 * Singleton instance of this class.
	 */
	private static final Utils instance = new Utils();

	/*
	 * Application Flags
	 */
	public final boolean DEV_ENABLED = true;

	/*
	 * Class object fields.
	 */
	private final TextHandler textHandler = TextHandler.getInstance();

	/*
	 * Private constructor. Can't be instantiated.
	 */
	private Utils() {
		if (DEV_ENABLED) logMessage(textHandler.devStartUpMessage);
	}

	/*
	 * Getter for the singleton instance.
	 */
	public static synchronized Utils getInstance() {
		return instance;
	}

	/*
	 * Log the message to the console. Not saved.
	 */
	public void logMessage(String message) {
		String messagePrefix = "[DEV-LOG " + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] ";
		message = messagePrefix + message;
		System.err.println(message);
	}

	/*
	 * SHA256 algorithm.
	 */
	public String sha256(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * Generate random password
	 */
	public String generatePassword() {
		String pwd = RandomStringUtils.random(24, textHandler.PASS_CHARS);
		return pwd;
	}

	/*
	 * Method use to sleep the thread for millis milliseconds. 
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}