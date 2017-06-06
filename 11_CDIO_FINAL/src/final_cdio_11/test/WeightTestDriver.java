package final_cdio_11.test;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.weight.ase.old.IWeightConnector;
import final_cdio_11.java.weight.ase.old.IWeightController;
import final_cdio_11.java.weight.ase.old.WeightConnector;
import final_cdio_11.java.weight.ase.old.WeightController;
import final_cdio_11.java.weight.ase.old.IWeightConnector.WeightException;

public class WeightTestDriver {

	public static void main(String[] args) {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		SQLRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());
		SQLReceptDAO rDAO = new SQLReceptDAO(Connector.getInstance());
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());
		IWeightConnector connector = new WeightConnector();

		IWeightController controller = new WeightController(oprDAO, roleDAO, rDAO, pbDAO, connector);

		try {
			controller.weightProcedure();
		} catch (WeightException e) {
			e.printStackTrace();
		}

	}

}