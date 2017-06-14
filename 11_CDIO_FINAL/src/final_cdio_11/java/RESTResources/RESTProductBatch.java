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

/*
 * RESTResource to handle productbatch related actions.
 */
@Path("/pb")
public class RESTProductBatch {

	private final IProductBatchController pbController = new ProductBatchController();

	/* Get Productbatch list */
	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductBatchDTO> RaavareBatchList() {
		return pbController.getProductBatchList();
	}

	/* Update ProductBatch */
	@POST
	@Path("/UpdatePB")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePB(PbPOJO data) {
		return pbController.updateProductBatch(data);
	}

	/* Delete ProductBatch */
	@POST
	@Path("/delPB/{pbId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(@PathParam("pbId") int id) {
		return pbController.deleteProductBatch(id);
	}

	/* ProductBatch creation */
	@POST
	@Path("/insertPB")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delPB(PbPOJO data) {
		return pbController.createProductBatch(data);
	}

	/* Get ProductBatch Recept List */
	@GET
	@Path("/repIdList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> repIdList() {
		return pbController.getProductBatchReceptIdList();
	}

}