package final_cdio_11.java.weight;

import java.io.IOException;

public interface IWeightConnector {
	void initConnection(); // initalize connection
	int getId(String message) throws IOException; // beder om laborant nummer
	int getWeight();
	void confirmMessage(String message);;
	void tara();
}