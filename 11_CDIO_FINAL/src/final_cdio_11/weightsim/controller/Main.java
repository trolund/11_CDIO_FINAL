package final_cdio_11.weightsim.controller;

import final_cdio_11.weightsim.socket.ISocketController;
import final_cdio_11.weightsim.socket.SocketController;
import final_cdio_11.weightsim.weight.IWeightInterfaceController;
import final_cdio_11.weightsim.weight.gui.WeightInterfaceControllerGUI;

/**
 * Simple class to fire up application and inject implementations
 * 
 * @author Christian
 *
 */
public class Main {
	@SuppressWarnings("unused")
	private static boolean gui = true;

	public static void main(String[] args) {
		ISocketController socketHandler = new SocketController();
		IWeightInterfaceController weightController = new WeightInterfaceControllerGUI();
		// Injecting socket and uiController into mainController - Replace with
		// improved versions...
		IMainController mainCtrl = new MainController(socketHandler, weightController);
		// .init and .start could be merged
		mainCtrl.start();
	}
}