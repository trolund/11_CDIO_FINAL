package final_cdio_11.java.weight.ase;

public interface IWeightConnector {
	void initConnection() throws WeightConnectionException;
	void closeConnection() throws WeightConnectionException;
	int getId(String message) throws WeightException;
	int getWeight() throws WeightException;
	void taraWeight() throws WeightException;
	void confirmMessage(String message) throws WeightException;

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