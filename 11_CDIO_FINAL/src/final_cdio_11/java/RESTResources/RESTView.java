package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import final_cdio_11.java.RESTResources.controller.IViewController;
import final_cdio_11.java.RESTResources.controller.ViewController;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

@Path("/View")
public class RESTView {

	private final IViewController viewController = new ViewController();

	@GET
	@Path("/VUserTableList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VUserTableDTO> VUserTableList() {
		return viewController.VUserTableList();
	}

}