package final_cdio_11.java.weight.ase;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightException;

public class WeightTestDriver {

	public static void main(String[] args) {
		/* Running in own thread so it won't stall the Tomcat thread. */
		new Thread(new Runnable() {
			@Override
			public void run() {
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
		}).start();
	}

}