package final_cdio_11.java.weight.sim.socket;

public interface ISocketController extends Runnable {
	public final static int PORT = 8000;
	void registerObserver(ISocketObserver observer);
	void unRegisterObserver(ISocketObserver observer);
	void notifyObservers(SocketInMessage message);
	void sendMessage(SocketOutMessage message);
}