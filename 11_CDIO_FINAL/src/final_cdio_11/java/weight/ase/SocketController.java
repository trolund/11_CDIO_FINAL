package final_cdio_11.java.weight.ase;

import java.util.HashSet;
import java.util.Set;

public class SocketController implements ISocketController, Runnable {

	private Set<ISocketObserver> observerList = new HashSet<>();

	@Override
	public void run() {

	}

	@Override
	public void registerObserver(ISocketObserver socketObserver) {
		observerList.add(socketObserver);
	}

	@Override
	public void unRegisterObserver(ISocketObserver socketObserver) {
		observerList.remove(socketObserver);
	}

	@Override
	public void notifyObservers(String observerMessage) {
		for (ISocketObserver observer : observerList)
			observer.notify(observerMessage);
	}

	@Override
	public void sendSocketMessage(String socketMessage) {

	}

}