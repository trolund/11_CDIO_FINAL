package final_cdio_11.java.RESTResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import final_cdio_11.java.RESTResources.controller.IRESTWeightController;
import final_cdio_11.java.RESTResources.controller.RESTWeightController;

@Path("/weight")
public class RESTWeight {

	private final IRESTWeightController weightController = new RESTWeightController();

	@GET
	@Path("/start")
	public void start() {
		weightController.start();
	}

}