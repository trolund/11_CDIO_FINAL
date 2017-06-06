package final_cdio_11.java.weight.ase;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import final_cdio_11.java.weight.ase.old.IWeightConnector.WeightConnectionException;

public class SocketConnector implements ISocketConnector {

	@Override
	public void initConnection() throws SocketConnectionException {
		if (weightSocket != null && weightSocket.isConnected()) return;
		for (String ip : textHandler.WEIGHT_IPS) {
			try {
				if (InetAddress.getByName(ip).isReachable(100)) {
					weightSocket = new Socket(ip, textHandler.WEIGHT_PORT);
					break;
				} else {
					utils.logMessage(ip + ":" + textHandler.WEIGHT_PORT + " not reachable. Trying again.");
				}
			} catch (UnknownHostException e) {
				throw new WeightConnectionException(e.getMessage(), e);
			} catch (IOException e) {
				throw new WeightConnectionException(e.getMessage(), e);
			}
		}

		if (weightSocket != null && weightSocket.isConnected()) {
			utils.logMessage("Socket connection established: " + weightSocket.getLocalSocketAddress() + ":" + textHandler.WEIGHT_PORT);
		} else {
			throw new WeightConnectionException("Socket connection failed.");
		}
	}

	@Override
	public void closeConnection() throws SocketConnectionException {
		try {
			if (!weightSocket.isClosed()) weightSocket.close();
			utils.logMessage("Socket connection closed successfully.");
		} catch (IOException e) {
			throw new WeightConnectionException("Failed to close socket connection.");
		}
	}

}