package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import final_cdio_11.java.RESTResources.controller.IViewController;
import final_cdio_11.java.RESTResources.controller.ViewController;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.IVAdminForemanPBCDAO;
import final_cdio_11.java.data.dao.view.IVUserTableDAO;
import final_cdio_11.java.data.dao.view.IVForemanOperatorDAO;
import final_cdio_11.java.data.dao.view.IVOperatorRBDAO;
import final_cdio_11.java.data.dao.view.IVOperatorRecipeDAO;
import final_cdio_11.java.data.dao.view.IVPharmacistRecipeDAO;
import final_cdio_11.java.data.dao.view.SQLAdminForemanPBCDAO;
import final_cdio_11.java.data.dao.view.SQLVUserTableDAO;
import final_cdio_11.java.data.dao.view.SQLForemanOperatorDAO;
import final_cdio_11.java.data.dao.view.SQLOperatorRBDAO;
import final_cdio_11.java.data.dao.view.SQLOperatorRecipeDAO;
import final_cdio_11.java.data.dao.view.SQLPharmacistRecipeDAO;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;
import final_cdio_11.java.data.dto.view.VUserTableDTO;
import final_cdio_11.java.data.dto.view.VForemanOperatorDTO;
import final_cdio_11.java.data.dto.view.VOperatorRBDTO;
import final_cdio_11.java.data.dto.view.VOperatorRecipeDTO;
import final_cdio_11.java.data.dto.view.VPharmacistRecipeDTO;

@Path("/View")
public class RESTView {

	private final IViewController viewController = new ViewController();

	@GET
	@Path("/AdminForemanList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VAdminForemanPBCDTO> AdminForemanList() {
		IVAdminForemanPBCDAO view = new SQLAdminForemanPBCDAO(Connector.getInstance());

		List<VAdminForemanPBCDTO> viewList = null;

		try {
			viewList = view.getVAdminForemanPBCList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@GET
	@Path("/AdminOperatorList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VUserTableDTO> AdminOperatorList() {
		IVUserTableDAO view = new SQLVUserTableDAO(Connector.getInstance());

		List<VUserTableDTO> viewList = null;

		try {
			viewList = view.getVUserTableList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@GET
	@Path("/FormanOperatorList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VForemanOperatorDTO> FormanOperatorList() {
		IVForemanOperatorDAO view = new SQLForemanOperatorDAO(Connector.getInstance());

		List<VForemanOperatorDTO> viewList = null;

		try {
			viewList = view.getVForemanOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@GET
	@Path("/OperatorRBList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VOperatorRBDTO> OperatorRBList() {
		IVOperatorRBDAO view = new SQLOperatorRBDAO(Connector.getInstance());

		List<VOperatorRBDTO> viewList = null;

		try {
			viewList = view.getVOperatorRBList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@GET
	@Path("/OperatorRecipeList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VOperatorRecipeDTO> OperatorRecipeList() {
		IVOperatorRecipeDAO view = new SQLOperatorRecipeDAO(Connector.getInstance());

		List<VOperatorRecipeDTO> viewList = null;

		try {
			viewList = view.getVOperatorRecipeList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@GET
	@Path("/PharmacistRecipeList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VPharmacistRecipeDTO> PharmacistRecipeList() {
		IVPharmacistRecipeDAO view = new SQLPharmacistRecipeDAO(Connector.getInstance());

		List<VPharmacistRecipeDTO> viewList = null;

		try {
			viewList = view.getVPharmacistRecipeList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}
	
	
	@GET
	@Path("/VUserTableList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VUserTableDTO> VUserTableList() {
		return viewController.VUserTableList();
	}

}