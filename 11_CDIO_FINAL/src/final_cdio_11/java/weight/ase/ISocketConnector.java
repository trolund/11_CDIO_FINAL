package final_cdio_11.java.weight.ase;

public interface ISocketConnector {
	void initConnection() throws SocketConnectionException;
	void closeConnection() throws SocketConnectionException;

	public class SocketConnectionException extends Exception {
		private static final long serialVersionUID = 1L;

		public SocketConnectionException(String msg, Throwable e) {
			super(msg, e);
		}

		public SocketConnectionException(String msg) {
			super(msg);
		}
	}

}