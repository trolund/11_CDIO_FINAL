package final_cdio_11.java.weight.ase;

/*
 * Interface for the weight connector class.
 */
public interface IWeightConnector {
	void initConnection() throws WeightConnectionException;
	void closeConnection() throws WeightConnectionException;
	int rm208Message(String message) throws WeightException;
	double getCurrentWeight() throws WeightException;
	double tareWeight() throws WeightException;
	void confirmMessage(String message) throws WeightException;
	void secondaryDisplayMessage(String message, int sleepMillis) throws WeightException;
	void clearSecondaryDisplay() throws WeightException;
	void sendK3Message() throws WeightException;
	void sendK1Message() throws WeightException;
	void sendConfirmMessage(String message) throws WeightException;
	String shortString(String message, int maxLength);
	void promptQuit() throws WeightException;

	/*
	 * Custom WeightException.
	 */
	public class WeightException extends Exception {
		private static final long serialVersionUID = 1L;

		public WeightException(String msg, Throwable e) {
			super(msg, e);
		}

		public WeightException(String msg) {
			super(msg);
		}
	}

	/*
	 * Custom WeightException specific for connection related issues.
	 */
	public class WeightConnectionException extends WeightException {
		private static final long serialVersionUID = 1L;

		public WeightConnectionException(String msg, Throwable e) {
			super(msg, e);
		}

		public WeightConnectionException(String msg) {
			super(msg);
		}
	}

}