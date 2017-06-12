package final_cdio_11.java.weight.sim.controller;

import final_cdio_11.java.weight.sim.socket.ISocketController;
import final_cdio_11.java.weight.sim.weight.IWeightInterfaceController;

public interface IMainController {
	void init(ISocketController socketHandler, IWeightInterfaceController uiController);

	void start();

}