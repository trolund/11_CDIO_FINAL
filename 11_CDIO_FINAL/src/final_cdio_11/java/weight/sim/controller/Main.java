package final_cdio_11.java.weight.sim.controller;

import final_cdio_11.java.weight.sim.socket.ISocketController;
import final_cdio_11.java.weight.sim.socket.SocketController;
import final_cdio_11.java.weight.sim.weight.IWeightInterfaceController;
import final_cdio_11.java.weight.sim.weight.gui.WeightInterfaceControllerGUI;

/**
 * Simple class to fire up application and inject implementations
 * @author Christian
 *
 */
public class Main {

	public static void main(String[] args) {
		ISocketController socketHandler = new SocketController();
		IWeightInterfaceController weightController = new WeightInterfaceControllerGUI();
		//Injecting socket and uiController into mainController - Replace with improved versions...
		IMainController mainCtrl = new MainController(socketHandler, weightController);
		//.init and .start could be merged
		mainCtrl.start();
	}

}