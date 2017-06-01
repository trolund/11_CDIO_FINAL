package final_cdio_11.weightsim.weight;

public interface IWeightInterfaceObserver {
	void notifyKeyPress(KeyPress keypress);

	void notifyWeightChange(double newWeight);
}