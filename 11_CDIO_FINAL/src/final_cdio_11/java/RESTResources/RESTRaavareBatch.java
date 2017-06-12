package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.controller.IRaavareBatchController;
import final_cdio_11.java.RESTResources.controller.RaavareBatchController;
import final_cdio_11.java.data.dto.RaavareBatchDTO;

@Path("/mb")
public class RESTRaavareBatch {

	private final IRaavareBatchController rbController = new RaavareBatchController();

	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RaavareBatchDTO> RaavareBatchList() {
		return rbController.getRaavareBatchList();
	}

	@POST
	@Path("/Update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePB(RaavareBatchDTO data) {
		return rbController.updateRaavareBatch(data);
	}

	@POST
	@Path("/del/{pbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(@PathParam("pbId") int id) {
		return rbController.deleteRaavareBatch(id);
	}

	@POST
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(RaavareBatchDTO data) {
		return rbController.createRaavareBatch(data);
	}

}