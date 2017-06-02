package final_cdio_11.java.weight;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.weight.IWeightConnector.WeightException;

public interface IWeightController {
	void weightProcedure() throws WeightException, DALException;
}