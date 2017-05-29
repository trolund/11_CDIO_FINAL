package final_cdio_11.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.IVAdminForemanPBCDAO;
import final_cdio_11.java.data.dao.view.SQLAdminForemanPBCDAO;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;

@Path("/View")
public class RESTView {

	@GET
	@Path("/getAdminForemanList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<VAdminForemanPBCDTO> getAdminForemanList() {
		IVAdminForemanPBCDAO viewDAO = new SQLAdminForemanPBCDAO(Connector.getInstance());
		List<VAdminForemanPBCDTO> viewList = null;

		try {
			viewList = viewDAO.getVAdminForemanPBCList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return viewList;
	}

}