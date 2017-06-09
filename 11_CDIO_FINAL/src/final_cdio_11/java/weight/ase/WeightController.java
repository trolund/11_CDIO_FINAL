package final_cdio_11.java.weight.ase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IRaavareDAO;
import final_cdio_11.java.data.dao.IReceptComponentDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
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
	private IReceptComponentDAO rcDAO;
	private IRaavareDAO raavareDAO;
	private IWeightConnector weightConnector;

	public WeightController(IOperatorDAO oprDAO, IRoleDAO roleDAO, IReceptDAO receptDAO, IProductBatchDAO pbDAO, IRaavareDAO raavareDAO, IReceptComponentDAO rcDAO, IWeightConnector weightConnector) {
		this.oprDAO = oprDAO;
		this.roleDAO = roleDAO;
		this.receptDAO = receptDAO;
		this.pbDAO = pbDAO;
		this.raavareDAO = raavareDAO;
		this.rcDAO = rcDAO;
		this.weightConnector = weightConnector;
	}

	@Override
	public void weightProcedure() throws WeightException {

		/* Initialize connection */
		try {
			weightConnector.initConnection();
			weightConnector.sendK3Message();
		} catch (WeightConnectionException e) {
			e.printStackTrace();
		}

		/* Step 3: Få laborant nummer */
		int oprId = -1;
		boolean isAuthorized = false;
		OperatorDTO oprDTO = null;

		/* Loop until an authorized operator puts an id. */
		do {
			/* Get operator id */
			try {
				oprId = weightConnector.rm208Message("Enter oprId. Press OK");
			} catch (WeightException e) {
				e.printStackTrace();
			}

			/* Check if the operator is authorized. */
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
					weightConnector.sendConfirmMessage(unauthMessage);
					weightConnector.clearSecondaryDisplay();
				}
			} catch (DALException e1) {
				weightConnector.sendConfirmMessage("Invalid oprId. Press OK");
				weightConnector.clearSecondaryDisplay();
				isAuthorized = false;
			}
		} while (!isAuthorized);

		/* Step 4: vægt svarer tilbage med laborant navn */
		try {
			String oprNameGreeting = oprDTO.getOprFirstName() + ". Press OK";
			oprNameGreeting = weightConnector.shortString(oprNameGreeting, 29);
			weightConnector.sendConfirmMessage(oprNameGreeting);
			weightConnector.clearSecondaryDisplay();
		} catch (WeightException e) {
			e.printStackTrace();
		}

		/* Step 5: Laboranten indtaster produktbatchnummer */
		int pbId = -1;
		boolean isPbIdValid = false;
		boolean isReceptIdValid = false;
		int receptId = -1;

		do {
			do {
				try {
					pbId = weightConnector.rm208Message("Enter pbId. Press OK");
					isPbIdValid = true;
				} catch (WeightException e) {
					weightConnector.confirmMessage("Invalid pbId. Press OK");
				}

				try {
					pbDAO.getProductBatch(pbId).getReceptId();
				} catch (DALException e) {
					weightConnector.sendConfirmMessage("Invalid receptId. Press OK");
					isPbIdValid = false;
				}
			} while (!isPbIdValid);

			/* Step 6: Vægt svarer tilbage med navn på recept der skal produceres */
			try {
				receptId = pbDAO.getProductBatch(pbId).getReceptId();
				isReceptIdValid = true;
			} catch (DALException e) {
				weightConnector.confirmMessage("rId " + receptId + " is not valid");
				isPbIdValid = false;
			}
		} while (!isReceptIdValid);

		String receptName = null;
		try {
			receptName = receptDAO.getRecept(receptId).getReceptName();
		} catch (DALException e) {
			e.printStackTrace();
		}

		weightConnector.sendConfirmMessage(receptName + " was found");
		weightConnector.clearSecondaryDisplay();

		List<ReceptComponentDTO> rcList = null;
		try {
			rcList = rcDAO.getReceptComponentList(receptId);
		} catch (DALException e1) {
			e1.printStackTrace();
		}

		/* Step 8: Produktbatchnummerets status sættes til "under produktion". */
		try {
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getpbId(), 1, pbDTO.getReceptId(), pbDTO.getStatus()));
		} catch (DALException e) {
			e.printStackTrace();
		}

		/* Step 7: Laborant tjekker vægt er nulstillet og trykker OK. */
		try {
			weightConnector.sendConfirmMessage("Unload weight to begin");
			weightConnector.clearSecondaryDisplay();
		} catch (WeightException e) {
			e.printStackTrace();
		}

		/* Step 9: Tarér vægt */
		try {
			weightConnector.tareWeight();
		} catch (WeightException e) {
			weightConnector.confirmMessage("Tare weight failed");
			throw new WeightException(e.getMessage(), e);
		}

		/* Step 10 og 11: Laborant placerer beholder og trykker OK */
		try {
			weightConnector.sendConfirmMessage("Place container on weight");
			weightConnector.clearSecondaryDisplay();
		} catch (WeightException e) {
			e.printStackTrace();
		}

		double totalRaavareWeight = 0;
		int rbId = -1;

		for (ReceptComponentDTO rcDTO : rcList) {

			String raavareName = null;
			try {
				raavareName = raavareDAO.getRaavare(rcDTO.getRaavareId()).getraavareName();
			} catch (DALException e1) {
				e1.printStackTrace();
			}

			double tareWeight = -1;
			try {
				tareWeight = weightConnector.tareWeight();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			try {
				weightConnector.sendConfirmMessage("Weighing: '" + raavareName + "'. Get it"); // bedre tekst?
				weightConnector.clearSecondaryDisplay();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			try {
				rbId = weightConnector.rm208Message("Enter rbId. Press OK");
			} catch (WeightException e) {
				e.printStackTrace();
			}

			try {
				weightConnector.sendConfirmMessage("Weighing: '" + raavareName + "'. Press OK"); // bedre tekst og tolerance
				weightConnector.clearSecondaryDisplay();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			/* Step 12: Vægten af tarabeholder registreres */
			double currentRaavareWeight = -1;
			try {
				currentRaavareWeight = weightConnector.getCurrentWeight();
				System.out.println("currentWeight: " + currentRaavareWeight);
				totalRaavareWeight = totalRaavareWeight + currentRaavareWeight;
			} catch (WeightException e) {
				e.printStackTrace();
			}
		}

		System.out.print("totalRaavareWeight: " + new DecimalFormat("#0.0000").format(totalRaavareWeight) + " kg\n");

		/* Step 15: Laborant afvejer og trykker OK. */
		weightConnector.sendConfirmMessage("Finished pb");
		weightConnector.clearSecondaryDisplay();

		/* Step 17: Produktbacthnummerets status sættes til afsluttet */
		try {
			System.out.println("Setting productbatch status to 2, afsluttet.");
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getpbId(), 2, pbDTO.getReceptId(), pbDTO.getStatus()));
		} catch (DALException e) {
			e.printStackTrace();
		}

		/* Step: Send K1 besked så vægten kan slukkes. */
		weightConnector.sendK1Message();

		weightConnector.promptQuit();

		/* Step 18: Stop tråd så ny laborant kan komme til? */
	}

}