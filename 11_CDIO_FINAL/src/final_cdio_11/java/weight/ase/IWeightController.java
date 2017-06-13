package final_cdio_11.java.weight.ase;

import final_cdio_11.java.weight.ase.IWeightConnector.WeightConnectionException;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightException;

/*
 * Interface for the weight controller.
 */
public interface IWeightController {
	void weightProcedure() throws WeightException;
}