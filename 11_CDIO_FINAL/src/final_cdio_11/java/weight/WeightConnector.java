package final_cdio_11.java.weight;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class WeightConnector implements IWeightConnector {

	@Override
	public void initConnection() {

	}

	@Override
	public int getId(String message) throws IOException {

		Socket socket = new Socket("169.254.2.3", 8000);

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