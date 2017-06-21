package final_cdio_11.java.weight.sim.weight;

public interface IWeightInterfaceObserver {
	void notifyKeyPress(KeyPress keypress);

	void notifyWeightChange(double newWeight);
}