package final_cdio_11.java.weight;

public interface IWeightConnector {
	void initConnection() throws WeightConnectionException; // initalize connection
	int getId(String message) throws WeightException; // beder om laborant nummer
	int getWeight() throws WeightException;
	void confirmMessage(String message) throws WeightException;
	void tara() throws WeightException;

	public class WeightException extends Exception {
		private static final long serialVersionUID = 1L;

		public WeightException(String msg, Throwable e) {
			super(msg, e);
		}

		public WeightException(String msg) {
			super(msg);
		}
	}

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