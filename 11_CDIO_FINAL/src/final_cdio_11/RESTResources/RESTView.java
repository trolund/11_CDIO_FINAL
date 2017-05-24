package final_cdio_11.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.view.IVAdminForemanPBCDAO;
import final_cdio_11.java.data.dao.view.IVAdminOperatorDAO;
import final_cdio_11.java.data.dao.view.SQLAdminForemanPBCDAO;
import final_cdio_11.java.data.dao.view.SQLAdminOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;
import final_cdio_11.java.data.dto.view.VAdminOperatorDTO;

@Path("/View")
public class RESTView {
	
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
	public List<VAdminOperatorDTO> AdminOperatorList() {
		IVAdminOperatorDAO view = new SQLAdminOperatorDAO(Connector.getInstance());

		List<VAdminOperatorDTO> viewList = null;

		try {
			viewList = view.getVAdminOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

}
