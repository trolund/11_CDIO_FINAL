package final_cdio_11.java.data;

public interface IWeightConnector {
	void initConnection(); // initalize connection
	int getId(String message); // beder om laborant nummer
	int getWeight();
	void confirmMessage(String message);;
	void tara();
}