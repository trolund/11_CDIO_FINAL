package final_cdio_11.java.weight.ase;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IRaavareDAO;
import final_cdio_11.java.data.dao.IReceptComponentDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dao.SQLRaavareDAO;
import final_cdio_11.java.data.dao.SQLReceptComponentDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightException;

public class WeightTestDriver {

	public static void main(String[] args) {
		/* Running in own thread so it won't stall the Tomcat thread. */
		new Thread(new Runnable() {
			@Override
			public void run() {
				IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
				IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());
				IReceptDAO rDAO = new SQLReceptDAO(Connector.getInstance());
				IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());
				IProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());
				IReceptComponentDAO rcDAO = new SQLReceptComponentDAO(Connector.getInstance());
				IWeightConnector connector = new WeightConnector();

				IWeightController controller = new WeightController(oprDAO, roleDAO, rDAO, pbDAO, raavareDAO, rcDAO, connector);

				try {
					controller.weightProcedure();
				} catch (WeightException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

}