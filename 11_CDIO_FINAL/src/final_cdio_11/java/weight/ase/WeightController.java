package final_cdio_11.java.weight.ase;

import java.util.ArrayList;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.data.dto.RoleDTO;
import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightConnectionException;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightException;

public class WeightController implements IWeightController {

	private IOperatorDAO oprDAO;
	private IRoleDAO roleDAO;
	private IReceptDAO receptDAO;
	private IProductBatchDAO pbDAO;
	private IWeightConnector weightConnector;

	private final TextHandler textHandler = TextHandler.getInstance();

	public WeightController(IOperatorDAO oprDAO, IRoleDAO roleDAO, IReceptDAO receptDAO, IProductBatchDAO pbDAO, IWeightConnector weightConnector) {
		this.oprDAO = oprDAO;
		this.roleDAO = roleDAO;
		this.receptDAO = receptDAO;
		this.pbDAO = pbDAO;
		this.weightConnector = weightConnector;
	}

	@Override
	public void weightProcedure() throws WeightException {
		try {
			weightConnector.initConnection();
		} catch (WeightConnectionException e) {
			e.printStackTrace();
		}

		// step 3: Få laborant nummer
		int id = -1;
		try {
			id = weightConnector.getId("Weight idle. Enter operator id and press OK to confirm.");
		} catch (WeightException e) {
			e.printStackTrace();
		}

		OperatorDTO oprDTO = null;
		try {
			oprDTO = oprDAO.getOperator(id);

			ArrayList<RoleDTO> roleList = (ArrayList<RoleDTO>) roleDAO.getOprRoles(oprDTO.getOprId());

			boolean isAuthorized = false;

			for (RoleDTO role : roleList) {
				if (role.getRoleName().equals(textHandler.ROLE_LABORANT)) {
					isAuthorized = true;
					break;
				}
			}

			if (!isAuthorized) {
				String unauthMessage = "Operator id " + oprDTO.getOprId() + " is not authorized to use this terminal. Please contact appropriate personnel.";
				weightConnector.confirmMessage(unauthMessage);
				throw new WeightException(unauthMessage);
			}

		} catch (DALException e) {
			weightConnector.confirmMessage("Operator id " + id + " doesn't exist.");
			throw new WeightException(e.getMessage(), e);
		}

		// Step 4: vægt svarer tilbage med laborant navn
		try {
			String message = "Welcome " + oprDTO.getOprFirstName() + " " + oprDTO.getOprLastName() + ". Press OK to confirm.";
			weightConnector.confirmMessage(message);
		} catch (WeightException e) {
			e.printStackTrace();
		}

		// Step 5: Laboranten indtaster produktbatchnummer
		int pbId = -1;
		try {
			pbId = weightConnector.getId("Enter product batch id and press OK to confirm.");
		} catch (WeightException e) {
			weightConnector.confirmMessage("Product batch id " + pbId + " does not exist.");
			throw new WeightException(e.getMessage(), e);
		}

		// Step 6: Vægt svarer tilbage med navn på recept der skal produceres
		// Step 7: Laborant tjekker vægt er nulstillet og trykker OK
		int receptId = -1;
		try {
			receptId = pbDAO.getProductBatch(pbId).getReceptId();
		} catch (DALException e) {
			weightConnector.confirmMessage("Receipt id " + receptId + " does not exist.");
			throw new WeightException(e.getMessage(), e);
		}

		// Step 6 og 7: Laborant tjekker vægt er nulstillet og trykker OK
		String receptName = null;
		try {
			receptName = receptDAO.getRecept(receptId).getReceptName();
		} catch (DALException e) {
			e.printStackTrace();
		}

		try {
			weightConnector.confirmMessage("Receipt '" + receptName + "' from product batch id " + pbId + " was found. Make sure the weight is unloaded. Press OK to tare the weight.");
		} catch (WeightException e) {
			e.printStackTrace();
		}

		// Step 8: Produktbatchnummerets status sættes til "under produktion"
		try {
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getpbId(), 1, pbDTO.getReceptId(), pbDTO.getStatus()));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Step 9: Tarér vægt
		try {
			weightConnector.tareWeight();
		} catch (WeightException e) {
			weightConnector.confirmMessage("Tare weight failed.");
			throw new WeightException(e.getMessage(), e);
		}

		// Step 10 og 11: Laborant placerer beholder og trykker OK
		try {
			weightConnector.confirmMessage("Place the first tare container onto the weight. Press OK to confirm.");
		} catch (WeightException e) {
			e.printStackTrace();
		}

		// Step 12: Vægten af tarabeholder registreres
		try {
			weightConnector.getWeight();
		} catch (WeightException e) {
			e.printStackTrace();
		}
		//
		//		// Step 13: Vægten Tareres igen
		//		try {
		//			weightConnector.tareWeight();
		//		} catch (WeightException e) {
		//			e.printStackTrace();
		//		}
		//
		//		// Step 14 og 15: Vægt beder om råvarebatchnummer på første råvare.
		//		// Laborant afvejer og trykker OK
		//		try {
		//			int rbId = weightConnector.getId("Indtast raavarebatchID");
		//		} catch (WeightException e) {
		//			e.printStackTrace();
		//		}
		//
		//		// Step 16: Spørg laborant om raavareafvejning er afsluttet
		//		try {
		//			weightConnector.confirmMessage("Tast 1 afvej næste raavare eller afslut batch");
		//		} catch (WeightException e) {
		//			e.printStackTrace();
		//		}
		//
		//		// Step 17: Produktbacthnummerets status sættes til afsluttet
		//		ProductBatchDTO pbDTO2 = pbDAO.getProductBatch(pbId);
		//		pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO2.getpbId(), 2, pbDTO2.getReceptId(), pbDTO2.getStatus()));
		//
		//		// Step 18: Bed laboborant om enten at lave nyt batch eller afslutte
		//		try {
		//			weightConnector.confirmMessage("Tast 1 for ny afvejning eller tast 2 for at afslutte");
		//		} catch (WeightException e) {
		//			e.printStackTrace();
		//		}

	}

}