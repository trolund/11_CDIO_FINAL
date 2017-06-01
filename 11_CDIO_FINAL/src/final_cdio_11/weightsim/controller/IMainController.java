package final_cdio_11.weightsim.controller;

import final_cdio_11.weightsim.socket.ISocketController;
import final_cdio_11.weightsim.weight.IWeightInterfaceController;

public interface IMainController {
	void init(ISocketController socketHandler, IWeightInterfaceController uiController);
	void start();
}