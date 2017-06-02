package final_cdio_11.java.weight;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.ProductBatchDTO;

public class WeightController implements IWeightController {

	private IOperatorDAO oprDAO;
	private IReceptDAO receptDAO;
	private IProductBatchDAO pbDAO;
	private IWeightConnector iWeightConnector;

	public WeightController(IOperatorDAO oprDAO, IReceptDAO receptDAO, IProductBatchDAO pbDAO,
			IWeightConnector iWeightConnector) {
		this.oprDAO = oprDAO;
		this.receptDAO = receptDAO;
		this.pbDAO = pbDAO;
		this.iWeightConnector = iWeightConnector;
	}

	@Override
	public void weightProcedure() throws DALException {

		// step 3: Få laborant nummer
		int id = iWeightConnector.getId("Indtast dit laborant nummer");

		OperatorDTO oprDTO = oprDAO.getOperator(id);

		// Step 4: vægt svarer tilbage med laborant navn
		iWeightConnector.confirmMessage("Du er " + oprDTO.getOprFirstName() + " " + oprDTO.getOprLastName());

		// Step 5: Laboranten indtaster produktbatchnummer
		int pbId = iWeightConnector.getId("Indtast produktbatchID");

		// Step 6: Vægt svarer tilbage med navn på recept der skal produceres
		int receptID = pbDAO.getProductBatch(pbId).getReceptId();
		String receptName = receptDAO.getRecept(receptID).getReceptName();
		
		// Step 7: Laborant tjekker vægt er nulstillet og trykker OK
		iWeightConnector.confirmMessage("Kontrollér at vægten er nulstillet og tryk OK");

		// Step 8: Produktbatchnummerets status sættes til "under produktion"
		ProductBatchDTO pbDTO = pbDAO.getProductBatch(pbId);
		pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO.getpbId(), 1, pbDTO.getReceptId(), pbDTO.getStatus()));
	
		// Step 9: Tarér vægt
		iWeightConnector.tara();
		
		//Step 10 og 11: Laborant placerer beholder og trykker OK
		iWeightConnector.confirmMessage("Placér første tara beholder og tryk OK");
		
		//Step 12: Vægten af tarabeholder registreres
		iWeightConnector.getWeight();
		
		//Step 13: Vægten Tareres igen
		iWeightConnector.tara();
		
		//Step 14 og 15: Vægt beder om råvarebatchnummer på første råvare. Laborant afvejer og trykker OK
		int rbId = iWeightConnector.getId("Indtast raavarebatchID");
		
		//Step 16: Spørg laborant om raavareafvejning er afsluttet
		iWeightConnector.confirmMessage("Tast 1 afvej næste raavare eller afslut batch");
		
		//Step 17: Produktbacthnummerets status sættes til afsluttet
		ProductBatchDTO pbDTO2 = pbDAO.getProductBatch(pbId);
		pbDAO.updateProductBatch(new ProductBatchDTO(pbDTO2.getpbId(), 2, pbDTO2.getReceptId(), pbDTO2.getStatus()));
	
		// Step 18: Bed laboborant om enten at lave nyt batch eller afslutte
		iWeightConnector.confirmMessage("Tast 1 for ny afvejning eller tast 2 for at afslutte");
	
	}

}