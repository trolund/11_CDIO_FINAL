package final_cdio_11.java.RESTResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import final_cdio_11.java.RESTResources.controller.IViewController;
import final_cdio_11.java.RESTResources.controller.IrestWeightController;
import final_cdio_11.java.RESTResources.controller.ViewController;
import final_cdio_11.java.RESTResources.controller.restWeightController;

@Path("/weight")
public class RESTWEIGHT {
	
	private final IrestWeightController Controller = new restWeightController();
	
	@GET
	@Path("/start")
	public void start(){
		Controller.start();
	}

}
