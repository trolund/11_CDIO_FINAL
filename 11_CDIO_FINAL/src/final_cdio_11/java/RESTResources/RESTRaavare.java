package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.controller.IRaavareController;
import final_cdio_11.java.RESTResources.controller.RaavareController;
import final_cdio_11.java.data.dto.RaavareDTO;

/*
 * RESTResource to handle raavare related actions.
 */
@Path("/material")
public class RESTRaavare {

	private final IRaavareController raavareController = new RaavareController();

	/* Get raavare list */
	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RaavareDTO> RaavareBatchList() {
		return raavareController.getRaavareList();
	}

	/* Update raavare */
	@POST
	@Path("/Update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePB(RaavareDTO data) {
		return raavareController.updateRaavare(data);
	}

	/* Delete raavare */
	@POST
	@Path("/del/{pbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(@PathParam("pbId") int id) {
		return raavareController.deleteRaavare(id);
	}

	/* Create raavare */
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(RaavareDTO data) {
		return raavareController.createRaavare(data);
	}

}