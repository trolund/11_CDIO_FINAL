package final_cdio_11.java.weight.ase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
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
				if (InetAddress.getByName(ip).isReachable(100)) {
					weightSocket = new Socket(ip, textHandler.WEIGHT_PORT);
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
	public void closeConnection() throws WeightConnectionException {
		try {
			if (!weightSocket.isClosed()) weightSocket.close();
			utils.logMessage("Socket connection closed successfully.");
		} catch (IOException e) {
			throw new WeightConnectionException("Failed to close socket connection.");
		}
	}

	@Override
	public int getId(String message) throws WeightException {
		BufferedReader br = getSocketReader();
		PrintWriter pw = getSocketWriter();

		String socketMessage = "RM20 8 \"" + message + "\" \"\" \"&3\"\r\n";
		sendSocketMessage(socketMessage, pw);

		String data = null;

		try {
			data = br.readLine();
			System.out.println("Confirm message: '" + data + "'");
			data = br.readLine();
			System.out.println("Message with id: '" + data + "'");
		} catch (IOException e) {
			throw new WeightException("Failed to read weight input.", e);
		}

		System.out.println("substring: '" + data.substring(7, data.length()) + "'");
		int oprId = -1;
		try {
			oprId = Integer.parseInt(data.substring(7, data.length()));
			System.out.println("Extracted id: '" + oprId + "'");
		} catch (NumberFormatException e) {
			throw new WeightException("Failed to parse id.", e);
		}

		return oprId;
	}

	@Override
	public double getWeight() throws WeightException {
		BufferedReader br = getSocketReader();
		PrintWriter pw = getSocketWriter();

		String socketMessage = "S \r\n";
		sendSocketMessage(socketMessage, pw);

		String data = null;
		try {
			data = br.readLine();
			System.out.println("getWeight reply: " + data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		double weight = Double.parseDouble(data.substring(9, data.length() - 3));
		System.out.println("weight: '" + weight + "'");

		return weight;
	}

	@Override
	public void tareWeight() throws WeightException {
		BufferedReader br = getSocketReader();
		PrintWriter pw = getSocketWriter();

		String socketMessage = "T \r\n";
		sendSocketMessage(socketMessage, pw);

		try {
			String data = br.readLine();
			System.out.println("Tare reply: " + data);
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}
	}

	@Override
	public void confirmMessage(String message) throws WeightException {
		BufferedReader br = getSocketReader();
		PrintWriter pw = getSocketWriter();

		String socketMessage = "RM20 8 \"" + message + "\" \"\" \"&3\"\r\n";
		sendSocketMessage(socketMessage, pw);

		String data = null;
		try {
			data = br.readLine();
			System.out.println("Return message: '" + data + "'");
			data = br.readLine();
			System.out.println("Return message: '" + data + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedReader getSocketReader() throws WeightException {
		InputStreamReader in = null;
		try {
			in = new InputStreamReader(weightSocket.getInputStream());
			return new BufferedReader(in);
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}
	}

	private PrintWriter getSocketWriter() throws WeightException {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(weightSocket.getOutputStream(), true);
			return pw;
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}
	}

	private void sendSocketMessage(String socketMessage, PrintWriter pw) {
		pw.print(socketMessage);
		pw.flush();
	}

}