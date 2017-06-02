package final_cdio_11.java.weight;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import final_cdio_11.java.utils.TextHandler;

public class WeightConnector implements IWeightConnector {

	private final TextHandler textHandler = TextHandler.getInstance();
	private Socket weightSocket;

	@Override
	public void initConnection() throws weightconnectionexception {
		for (String ip : textHandler.WEIGHT_IPS) {
			try {
				weightSocket = new Socket(ip, textHandler.WEIGHT_PORT);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				throw new weightconnectionexception();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new weightconnectionexception();
			}
			if (weightSocket != null && weightSocket.isConnected()) {
				System.out.println("Socket er ikke null og forbundet til ip: " + ip + ":" + textHandler.WEIGHT_PORT);
			} else {
				System.out.println("You fucked up");
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