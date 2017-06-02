package final_cdio_11.java.weight;

import java.io.IOException;
import java.net.UnknownHostException;

public interface IWeightConnector {
	void initConnection() throws weightconnectionexception; // initalize connection
	int getId(String message); // beder om laborant nummer
	int getWeight();
	void confirmMessage(String message);
	void tara();
	
	public class weightconnectionexception extends Exception{
		
	}
}