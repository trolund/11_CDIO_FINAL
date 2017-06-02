package final_cdio_11.java.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.weight.IWeightConnector;
import final_cdio_11.java.weight.IWeightConnector.WeightException;
import final_cdio_11.java.weight.IWeightController;
import final_cdio_11.java.weight.WeightConnector;
import final_cdio_11.java.weight.WeightController;

public class netTEst {

	public static void main(String[] args) {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		SQLReceptDAO rDAO = new SQLReceptDAO(Connector.getInstance());
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());
		IWeightConnector Iconnector = new WeightConnector();
		
		
		
		
		IWeightController connector = new WeightController(oprDAO, rDAO, pbDAO, Iconnector);
			try {
				connector.weightProcedure();
			} catch (WeightException e) {
				e.printStackTrace();
			} catch (DALException e) {
				e.printStackTrace();
			}
		
//		try {
//			Socket socket = new Socket("169.254.2.3", 8000);
//			
//			if (socket.isConnected()) System.out.println("Socket forbundet til vægten!");
//			
//			InputStream is = socket.getInputStream();
//			OutputStream os = socket.getOutputStream();
//			
//			PrintWriter pw = new PrintWriter(os);
//			
//			pw.print("D \"fuck\"\r\n");
//			pw.flush();
//			
//			socket.close();
//			
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
}