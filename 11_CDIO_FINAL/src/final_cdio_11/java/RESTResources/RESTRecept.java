package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.controller.IReceptController;
import final_cdio_11.java.RESTResources.controller.ReceptController;
import final_cdio_11.java.data.dto.ReceptDTO;

/*
 * RESTResource to handle receipt related actions.
 */
@Path("/recept")
public class RESTRecept {

	private final IReceptController receptController = new ReceptController();

	/* Get receipt list */
	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReceptDTO> RaavareBatchList() {
		return receptController.getReceptList();
	}

	/* Update receipt */
	@POST
	@Path("/Update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePB(ReceptDTO data) {
		return receptController.updateRecept(data);
	}

	/* Delete receipt */
	@POST
	@Path("/del/{pbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(@PathParam("pbId") int id) {
		return receptController.deleteRecept(id);
	}

	/* Create receipt */
	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(ReceptDTO data) {
		return receptController.createRecept(data);
	}

}