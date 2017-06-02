package final_cdio_11.java.weight.ase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	public int getId(String message) throws WeightException {
		InputStreamReader in = null;
		try {
			in = new InputStreamReader(weightSocket.getInputStream());
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}

		BufferedReader br = new BufferedReader(in);

		PrintWriter pw = null;
		try {
			pw = new PrintWriter(weightSocket.getOutputStream(), true);
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}

		pw.print("RM20 8 \"" + message + "\" \"\" \"&3\"\r\n");
		pw.flush();

		String data = null;

		try {
			data = br.readLine();
			System.out.println("1: " + data);
			data = br.readLine();
			System.out.println("2: " + data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		int oprId = Integer.parseInt(data.substring(8, data.length() - 1));
		System.out.println(oprId);

		pw.close();
		try {
			br.close();
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}

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