package final_cdio_11.java.RESTResources;

import javax.ws.rs.Path;

import final_cdio_11.java.RESTResources.controller.IRaavareBatchController;
import final_cdio_11.java.RESTResources.controller.RaavareBatchController;

@Path("/rb")
public class RESTRaavareBatch {

	private final IRaavareBatchController rbController = new RaavareBatchController();
	
	

}