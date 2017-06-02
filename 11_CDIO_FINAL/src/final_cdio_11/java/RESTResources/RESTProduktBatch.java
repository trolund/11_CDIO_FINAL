package final_cdio_11.java.RESTResources;

import javax.ws.rs.Path;

import final_cdio_11.java.RESTResources.controller.IProduktBatchController;
import final_cdio_11.java.RESTResources.controller.ProduktBatchController;

@Path("/pb")
public class RESTProduktBatch {

	private final IProduktBatchController pbController = new ProduktBatchController();

}