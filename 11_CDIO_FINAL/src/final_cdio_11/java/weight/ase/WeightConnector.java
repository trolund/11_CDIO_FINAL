package final_cdio_11.java.weight.ase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import final_cdio_11.java.handler.TextHandler;
import final_cdio_11.java.utils.Utils;

public class WeightConnector implements IWeightConnector {

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();
	private Socket weightSocket;

	/* I/O objects */
	private BufferedReader in = null;
	private PrintWriter out = null;

	@Override
	public void initConnection() throws WeightConnectionException {
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
			initIO();

			String line = null;
			try {
				line = in.readLine();
				if (line.startsWith("I4")) utils.logMessage("Got this: " + line);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			throw new WeightConnectionException("Socket connection failed.");
		}
	}

	private void initIO() {
		try {
			in = getSocketReader();
			out = getSocketWriter();
		} catch (WeightException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeConnection() throws WeightConnectionException {
		try {
			if (!weightSocket.isClosed()) weightSocket.close();
			in.close();
			out.close();
			utils.logMessage("Socket connection closed successfully.");
		} catch (IOException e) {
			throw new WeightConnectionException("Failed to close socket connection.");
		}
	}

	@Override
	public int rm208Message(String message) throws WeightException {
		String socketMessage = "RM20 8 \"" + message + "\" \"\" \"&3\"\r\n";
		sendSocketMessage(socketMessage);

		String userMessage = null;

		try {
			String confirmMessage = in.readLine();

			if (confirmMessage.equals("RM20 C")) {
				throw new WeightException("Cancel was pressed.");
			}

			if (!confirmMessage.equals("RM20 B")) {
				throw new WeightException("Did not receive RM20 B. Got this: " + confirmMessage);
			}

			userMessage = in.readLine();

			if (userMessage.equals("RM20 C")) {
				throw new WeightException("Cancel was pressed.");
			}

			if (!userMessage.startsWith("RM20 A")) {
				throw new WeightException("Did not receive RM20 A. Got this: " + userMessage);
			}

		} catch (IOException e) {
			throw new WeightException("Failed to execute RM20 8.", e);
		}

		// real weight
		System.out.println("substring userMessage: '" + userMessage.substring(8, userMessage.length() - 1) + "'");

		// weight sim
		//System.out.println("substring userMessage: '" + userMessage.substring(7, userMessage.length()) + "'");

		int oprId = -1;
		try {
			oprId = Integer.parseInt(userMessage.substring(8, userMessage.length() - 1));
			//oprId = Integer.parseInt(userMessage.substring(7, userMessage.length()));
		} catch (NumberFormatException e) {
			throw new WeightException("Failed to parse id.", e);
		}

		return oprId;
	}

	@Override
	public double getCurrentWeight() throws WeightException {
		String socketMessage = "S\r\n";
		sendSocketMessage(socketMessage);

		String recMsg = null;
		try {
			recMsg = in.readLine();
			System.out.println("getWeight reply: " + recMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		double currentWeight = Double.parseDouble(recMsg.substring(9, recMsg.length() - 3));
		System.out.println("weight: '" + currentWeight + "'");

		return currentWeight;
	}

	@Override
	public double tareWeight() throws WeightException {
		String socketMessage = "T\r\n";
		sendSocketMessage(socketMessage);
		String tareReply = null;
		try {
			tareReply = in.readLine();
			System.out.println("Tare reply: '" + tareReply + "'");
			if (!tareReply.startsWith("T S")) {
				throw new WeightConnectionException("Failed to tare the weight. Did not receive T S.");
			}
			System.out.println("Tare reply: " + tareReply);
			System.out.println("Tare substring: " + tareReply.substring(8, tareReply.length() - 3));
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}
		double tareWeight = Double.parseDouble(tareReply.substring(8, tareReply.length() - 3));
		return tareWeight;
	}

	@Override
	public void confirmMessage(String message) throws WeightException {
		String socketMessage = "RM20 8 \"" + message + "\" \"\" \"&3\"\r\n";
		sendSocketMessage(socketMessage);

		String data = null;
		try {
			data = in.readLine();
			System.out.println("Return message: '" + data + "'");
			data = in.readLine();
			System.out.println("Return message: '" + data + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void secondaryDisplayMessage(String message) throws WeightException {
		String socketMessage = "P111 \"" + message + "\"\r\n";
		sendSocketMessage(socketMessage);

		try {
			String recMsg = in.readLine();
			System.out.println("secondaryDisplay: '" + recMsg + "'");

			if (!recMsg.startsWith("P111")) {
				throw new WeightException("Did not receive P111 A. Got this: " + recMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedReader getSocketReader() throws WeightException {
		try {
			if (in == null) in = new BufferedReader(new InputStreamReader(weightSocket.getInputStream()));
			return in;
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}
	}

	private PrintWriter getSocketWriter() throws WeightException {
		try {
			if (out == null) out = new PrintWriter(weightSocket.getOutputStream(), true);
			return out;
		} catch (IOException e) {
			throw new WeightException(e.getMessage(), e);
		}
	}

	private void sendSocketMessage(String socketMessage) {
		if (out == null) return;
		out.print(socketMessage);
		out.flush();
	}

}