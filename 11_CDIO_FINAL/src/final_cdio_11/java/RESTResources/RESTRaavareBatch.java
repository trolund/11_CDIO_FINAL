package final_cdio_11.java.RESTResources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import final_cdio_11.java.RESTResources.controller.IRaavareBatchController;
import final_cdio_11.java.RESTResources.controller.RaavareBatchController;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IRaavareBatchDAO;
import final_cdio_11.java.data.dao.SQLRaavareBatchDAO;
import final_cdio_11.java.data.dao.view.IVAdminForemanPBCDAO;
import final_cdio_11.java.data.dao.view.SQLAdminForemanPBCDAO;
import final_cdio_11.java.data.dto.RaavareBatchDTO;
import final_cdio_11.java.data.dto.view.VAdminForemanPBCDTO;

@Path("/rb")
public class RESTRaavareBatch {

	private final IRaavareBatchController rbController = new RaavareBatchController();
	
	

}