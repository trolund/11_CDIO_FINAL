package final_cdio_11.java.weight.ase;

public interface ISocketController {
	void registerObserver(ISocketObserver socketObserver);
	void unRegisterObserver(ISocketObserver socketObserver);
	void notifyObservers(String observerMessage);
	void sendSocketMessage(String socketMessage);
}