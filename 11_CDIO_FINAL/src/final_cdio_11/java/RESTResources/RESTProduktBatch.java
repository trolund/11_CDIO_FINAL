package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.controller.IProduktBatchController;
import final_cdio_11.java.RESTResources.controller.ProduktBatchController;
import final_cdio_11.java.RESTResources.pojo.PbPOJO;
import final_cdio_11.java.data.dto.ProductBatchDTO;

@Path("/pb")
public class RESTProduktBatch {

	private final IProduktBatchController pbController = new ProduktBatchController();

	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductBatchDTO> RaavareBatchList() {
		return pbController.ProduktBatchList();
	}
		
	@GET
	@Path("/UpdatePB")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePB(PbPOJO data) {
		return pbController.UpdatePB(data);
	}
	
	

}