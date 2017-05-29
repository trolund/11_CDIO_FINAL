package final_cdio_11.RESTResources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dto.ReceptDTO;

@Path("/recept")
public class RESTRecept {

	@GET
	@Path("/getById")
	@Produces(MediaType.TEXT_PLAIN)
	public ReceptDTO getReceptById(@QueryParam("receptId") String receptId) {
		SQLReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			return receptDAO.getRecept(Integer.parseInt(receptId));
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/getReceptList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReceptDTO> getReceptList() {
		SQLReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());
		List<ReceptDTO> recList = null;

		try {
			recList = receptDAO.getReceptList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return recList;
	}

	@DELETE
	@Path("/delReceptById")
	@Produces(MediaType.TEXT_PLAIN)
	public String delReceptById(@QueryParam("receptId") String receptId) {
		SQLReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.deleteRecept(Integer.parseInt(receptId));
			return "Recept with id: " + receptId + "deleted!";
		} catch (DALException e) {
			e.printStackTrace();
		}
		return "Error deleting recept - id: " + receptId;
	}

	@POST
	@Path("/createRecept")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReceptDTO createRecept(ReceptDTO receptDTO) {
		SQLReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.createRecept(receptDTO);
		} catch (DALException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Path("/updateRecept")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRecept(ReceptDTO receptDTO) {
		SQLReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.updateRecept(receptDTO);
			return Response.status(200).entity("Recept with id: " + receptDTO.getReceptId() + "name: " + receptDTO.getReceptName() + " - Updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return Response.status(400).entity("Error updating recept - id: " + receptDTO.getReceptId()).build();
	}

}