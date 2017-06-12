package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.controller.IProductBatchController;
import final_cdio_11.java.RESTResources.controller.ProductBatchController;
import final_cdio_11.java.RESTResources.pojo.PbPOJO;
import final_cdio_11.java.data.dto.ProductBatchDTO;

@Path("/pb")
public class RESTProductBatch {

	private final IProductBatchController pbController = new ProductBatchController();

	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductBatchDTO> RaavareBatchList() {
		return pbController.getProductBatchList();
	}

	@POST
	@Path("/UpdatePB")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePB(PbPOJO data) {
		return pbController.updateProductBatch(data);
	}

	@POST
	@Path("/delPB/{pbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(@PathParam("pbId") int id) {
		return pbController.deleteProductBatch(id);
	}

	@POST
	@Path("/insertPB")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(PbPOJO data) {
		return pbController.createProductBatch(data);
	}

	@GET
	@Path("/repIdList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> repIdList() {
		return pbController.getProductBatchReceptIdList();
	}

}