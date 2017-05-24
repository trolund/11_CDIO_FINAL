package utils;

import java.security.MessageDigest;

/*
 * Singleton class for security related utilities.
 */
public class SecUtils {

	/*
	 * Singleton instance of this class.
	 */
	private static final SecUtils instance = new SecUtils();

	/*
	 * private constructor. Can't be instantiated.
	 */
	private SecUtils() {

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
	 * Getter for the singleton instance.
	 */
	public static synchronized SecUtils getInstance() {
		return instance;
	}

}