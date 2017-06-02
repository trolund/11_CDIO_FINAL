package final_cdio_11.java.data;

import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dto.OperatorDTO;

public class WeightController implements IWeightController {

	private IOperatorDAO oprDAO;
	private IReceptDAO receptDAO;
	private IProductBatchDAO pbDAO;
	private IWeightConnector iWeightConnector;

	public WeightController(IOperatorDAO oprDAO, IReceptDAO receptDAO, IProductBatchDAO pbDAO, IWeightConnector iWeightConnector) {
		this.oprDAO = oprDAO;
		this.receptDAO = receptDAO;
		this.pbDAO = pbDAO;
		this.iWeightConnector = iWeightConnector;
	}

	@Override
	public void weightProcedure() throws DALException {
		
		int id = iWeightConnector.getId("Indtast dit laborant nummer");
		
		OperatorDTO oprDTO = oprDAO.getOperator(id);
		
		iWeightConnector.confirmMessage("Du er " + oprDTO.getOprFirstName() + " " + oprDTO.getOprLastName());
	
		
		
	}

}