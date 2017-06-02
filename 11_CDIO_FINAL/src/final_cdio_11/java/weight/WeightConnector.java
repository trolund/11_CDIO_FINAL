package final_cdio_11.java.weight;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;

public class WeightConnector implements IWeightConnector {

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();
	private Socket weightSocket;

	@Override
	public void initConnection() throws WeightConnectionException {
		for (String ip : textHandler.WEIGHT_IPS) {
			try {
				weightSocket = new Socket(ip, textHandler.WEIGHT_PORT);
			} catch (UnknownHostException e) {
				throw new WeightConnectionException(e.getMessage(), e);
			} catch (IOException e) {
				throw new WeightConnectionException(e.getMessage(), e);
			}

			if (weightSocket != null && weightSocket.isConnected()) {
				utils.logMessage("Socket connection established: " + ip + ":" + textHandler.WEIGHT_PORT);
			} else {
				throw new WeightConnectionException("Socket connection failed.");
			}
		}
	}

	@Override
	public int getId(String message) {
		return 0;
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public void confirmMessage(String message) {

	}

	@Override
	public void tara() {

	}

}