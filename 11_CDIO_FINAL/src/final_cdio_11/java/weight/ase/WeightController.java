package final_cdio_11.java.weight.ase;

import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IRaavareDAO;
import final_cdio_11.java.data.dao.IReceptComponentDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dao.SQLRaavareDAO;
import final_cdio_11.java.data.dao.SQLReceptComponentDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.data.dto.ReceptComponentDTO;
import final_cdio_11.java.data.dto.RoleDTO;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightConnectionException;
import final_cdio_11.java.weight.ase.IWeightConnector.WeightException;

public class WeightController implements IWeightController {

	private IOperatorDAO oprDAO;
	private IRoleDAO roleDAO;
	private IReceptDAO receptDAO;
	private IProductBatchDAO pbDAO;
	private IWeightConnector weightConnector;

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
			weightConnector.sendKMessage();
		} catch (WeightConnectionException e) {
			e.printStackTrace();
		}

		// step 3: Få laborant nummer
		int oprId = -1;
		boolean isAuthorized = false;
		OperatorDTO oprDTO = null;

		do {
			try {
				oprId = weightConnector.rm208Message("Enter oprId. Press OK");
			} catch (WeightException e) {
				e.printStackTrace();
			}

			try {
				oprDTO = oprDAO.getOperator(oprId);

				ArrayList<RoleDTO> roleList = (ArrayList<RoleDTO>) roleDAO.getOprRoles(oprDTO.getOprId());

				for (RoleDTO role : roleList) {
					if (role.getRoleName().equals(Role.Laborant.toString())) {
						isAuthorized = true;
						break;
					}
				}

				if (!isAuthorized) {
					String unauthMessage = "oprId " + oprDTO.getOprId() + " not authorized";
					weightConnector.confirmMessage(unauthMessage);
					//throw new WeightException(unauthMessage);
				}

			} catch (DALException e) {
				weightConnector.confirmMessage("oprId " + oprId + " doesn't exist");
				throw new WeightException(e.getMessage(), e);
			}
		} while (!isAuthorized);

		// Step 4: vægt svarer tilbage med laborant navn
		try {
			weightConnector.sendConfirmMessage(oprDTO.getOprFirstName() + ". Press OK to confirm");
			weightConnector.clearSecondaryDisplay();
		} catch (WeightException e) {
			e.printStackTrace();
		}

		// Step 5: Laboranten indtaster produktbatchnummer
		int pbId = -1;
		try {
			pbId = weightConnector.rm208Message("Enter pbId. Press OK");
		} catch (WeightException e) {
			weightConnector.confirmMessage("pbId " + pbId + " does not exist");
			throw new WeightException(e.getMessage(), e);
		}

		// Step 6: Vægt svarer tilbage med navn på recept der skal produceres
		// Step 7: Laborant tjekker vægt er nulstillet og trykker OK
		int receptId = -1;
		try {
			receptId = pbDAO.getProductBatch(pbId).getReceptId();
		} catch (DALException e) {
			weightConnector.confirmMessage("rId " + receptId + " does not exist");
			throw new WeightException(e.getMessage(), e);
		}

		String receptName = null;
		try {
			receptName = receptDAO.getRecept(receptId).getReceptName();
		} catch (DALException e) {
			e.printStackTrace();
		}

		weightConnector.sendConfirmMessage("'" + receptName + "' was found");
		weightConnector.clearSecondaryDisplay();

		// START LOOP HER!!!!!!!!!!!!!!!!!!!!!!

		IReceptComponentDAO rcDAO = new SQLReceptComponentDAO(Connector.getInstance());
		List<ReceptComponentDTO> rcList = null;
		try {
			rcList = rcDAO.getReceptComponentList(receptId);
		} catch (DALException e1) {
			e1.printStackTrace();
		}

		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		for (ReceptComponentDTO rcDTO : rcList) {

			String raavareName = null;
			try {
				raavareName = raavareDAO.getRaavare(rcDTO.getRaavareId()).getraavareName();
			} catch (DALException e1) {
				e1.printStackTrace();
			}

			System.out.println("rcDTO: " + rcDTO);
			System.out.println("raavareName: " + raavareName);

			// Step 6 og 7: Laborant tjekker vægt er nulstillet og trykker OK
			try {
				weightConnector.sendConfirmMessage("Unload for next material");
				weightConnector.clearSecondaryDisplay();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			try {
				weightConnector.sendConfirmMessage("Weighing: '" + raavareName + "'");
				weightConnector.clearSecondaryDisplay();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			// Step 8: Produktbatchnummerets status sættes til "under produktion"
			try {
				ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
				pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getpbId(), 1, pbDTO.getReceptId(), pbDTO.getStatus()));
			} catch (DALException e) {
				e.printStackTrace();
			}

			// Step 9: Tarér vægt
			try {
				weightConnector.tareWeight();
			} catch (WeightException e) {
				weightConnector.confirmMessage("Tare weight failed");
				throw new WeightException(e.getMessage(), e);
			}

			// Step 10 og 11: Laborant placerer beholder og trykker OK
			try {
				weightConnector.sendConfirmMessage("Put container");
				weightConnector.clearSecondaryDisplay();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			// Step 12: Vægten af tarabeholder registreres
			double currentWeightContainer = -1;
			try {
				currentWeightContainer = weightConnector.getCurrentWeight();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			double tareWeight = -1;
			try {
				tareWeight = weightConnector.tareWeight();
			} catch (WeightException e) {
				e.printStackTrace();
			}
			System.out.println("Got tare: " + tareWeight);

			//			// Step 14: Vægt beder om råvarebatchnummer på første råvare.
			//			int rbId = -1;
			//			try {
			//				rbId = weightConnector.rm208Message("Enter rbId");
			//			} catch (WeightException e) {
			//				e.printStackTrace();
			//			}

			// STOP LOOP HER!!!!!!!!!!
		}

		// Step 15: Laborant afvejer og trykker OK
		weightConnector.sendConfirmMessage("Press OK when done");
		weightConnector.clearSecondaryDisplay();

		double currentWeight = -1;
		try {
			currentWeight = weightConnector.getCurrentWeight();
		} catch (WeightException e) {
			e.printStackTrace();
		}

		// Step 16: Spørg laborant om raavareafvejning er afsluttet
		weightConnector.sendConfirmMessage("Press OK to Finish");
		weightConnector.clearSecondaryDisplay();

		// Step 17: Produktbacthnummerets status sættes til afsluttet
		try {
			System.out.println("Setting productbatch status to 2, afsluttet.");
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getpbId(), 2, pbDTO.getReceptId(), pbDTO.getStatus()));
		} catch (DALException e) {
			e.printStackTrace();
		}
		// Step 18: Stop tråd så ny laborant kan komme til?
	}

}