package final_cdio_11.java.RESTResources;

import javax.ws.rs.Path;

import final_cdio_11.java.RESTResources.controller.IRaavareController;
import final_cdio_11.java.RESTResources.controller.RaavareController;

@Path("/raavare")
public class RESTRaavare {

	private final IRaavareController raavareController = new RaavareController();

}