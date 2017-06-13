package final_cdio_11.java.weight.ase;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchComponentDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IRaavareDAO;
import final_cdio_11.java.data.dao.IReceptComponentDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.ProductBatchComponentDTO;
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
	private IProductBatchComponentDAO pbcDAO;
	private IReceptComponentDAO rcDAO;
	private IRaavareDAO raavareDAO;
	private IWeightConnector weightConnector;

	public WeightController(IOperatorDAO oprDAO, IRoleDAO roleDAO, IReceptDAO receptDAO, IProductBatchDAO pbDAO, IProductBatchComponentDAO pbcDAO, IRaavareDAO raavareDAO, IReceptComponentDAO rcDAO, IWeightConnector weightConnector) {
		this.oprDAO = oprDAO;
		this.roleDAO = roleDAO;
		this.receptDAO = receptDAO;
		this.pbDAO = pbDAO;
		this.pbcDAO = pbcDAO;
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
					String unauthMessage = "oprId not authorized";
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

		/* Step 8: Produktbatchnummerets status sættes til "under produktion". */
		try {
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getPbId(), 1, pbDTO.getReceptId(), pbDTO.getStatus()));
		} catch (DALException e) {
			e.printStackTrace();
		}

		double totalRaavareWeight = 0;
		int rbId = -1;

		for (ReceptComponentDTO rcDTO : rcList) {

			String raavareName = null;
			try {
				raavareName = raavareDAO.getRaavare(rcDTO.getRaavareId()).getRaavareName();
			} catch (DALException e1) {
				e1.printStackTrace();
			}

			double tareWeight = -1;
			try {
				tareWeight = weightConnector.tareWeight();
			} catch (WeightException e) {
				e.printStackTrace();
			}

			boolean isRbValid = false;
			do {
				try {
					rbId = weightConnector.rm208Message(raavareName + ". Enter rbId");
					isRbValid = true;
				} catch (WeightException e) {
					weightConnector.sendConfirmMessage("Invalid rbId");
				}
			} while (!isRbValid);

			double currentRaavareWeight = -1;
			double tolerance = -1;
			double nomNetto = -1;
			double nomBruttoUpper = -1;
			double nomBruttoLower = -1;	

			do {

				tolerance = rcDTO.getTolerance();
				nomNetto = rcDTO.getNomNetto();
				nomBruttoUpper = (nomNetto * tolerance) + nomNetto;
				nomBruttoLower = nomNetto - (nomNetto * tolerance);	


				try {			
					weightConnector.sendConfirmMessage("Min: " + nomBruttoLower + "kg., Max: " + nomBruttoUpper + "kg."); // bedre tekst og tolerance
					weightConnector.clearSecondaryDisplay();
				} catch (WeightException e) {
					e.printStackTrace();
				}

				/* Step 12: Vægten af tarabeholder registreres */

				try {
					currentRaavareWeight = weightConnector.getCurrentWeight();
					System.out.println("currentWeight: " + currentRaavareWeight);
					totalRaavareWeight = totalRaavareWeight + currentRaavareWeight;
				} catch (WeightException e) {
					e.printStackTrace();
				}

				if (currentRaavareWeight < nomBruttoLower) 
				{
					weightConnector.sendConfirmMessage("Weight too low. Min: " + nomBruttoLower + "kg.");
				}
				else if (currentRaavareWeight > nomBruttoUpper) 
				{
					weightConnector.sendConfirmMessage("Weight too high. Max: " + nomBruttoUpper + "kg.");
				}
				
			} while (currentRaavareWeight < nomBruttoLower || currentRaavareWeight > nomBruttoUpper);

			// tolerances skal beregnes
			ProductBatchComponentDTO pbcDTO = new ProductBatchComponentDTO(pbId, rbId, tareWeight, currentRaavareWeight, oprId, 0);
			try {
				pbcDAO.createProductBatchComponent(pbcDTO);
				System.out.println("Created: " + pbcDTO);
			} catch (DALException e) {
				weightConnector.sendConfirmMessage("Failed to create pbcDTO");
				e.printStackTrace();
			}

		}

		System.out.print("totalRaavareWeight: " + totalRaavareWeight);

		/* Step 15: Laborant afvejer og trykker OK. */
		weightConnector.sendConfirmMessage("Finished pb");
		weightConnector.clearSecondaryDisplay();

		/* Step 17: Produktbacthnummerets status sættes til afsluttet */
		try {
			System.out.println("Setting productbatch status to 2, afsluttet.");
			ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
			pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getPbId(), 2, pbDTO.getReceptId(), pbDTO.getStatus()));
		} catch (DALException e) {
			e.printStackTrace();
		}

		/* Step: Send K1 besked så vægten kan slukkes. */
		weightConnector.sendK1Message();

		weightConnector.promptQuit();

		/* Step 18: Stop tråd så ny laborant kan komme til? */
	}

}