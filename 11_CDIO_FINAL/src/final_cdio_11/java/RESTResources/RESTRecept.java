package final_cdio_11.java.RESTResources;

import javax.ws.rs.Path;

import final_cdio_11.java.RESTResources.controller.IReceptController;
import final_cdio_11.java.RESTResources.controller.ReceptController;

@Path("/recept")
public class RESTRecept {

	private final IReceptController receptController = new ReceptController();

	//	@GET
	//	@Path("/getById")
	//	@Produces(MediaType.TEXT_PLAIN)
	//	public ReceptDTO getReceptById(@QueryParam("receptId") String receptId) {
	//		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());
	//
	//		try {
	//			return receptDAO.getRecept(Integer.parseInt(receptId));
	//		} catch (DALException e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}
	//
	//	@GET
	//	@Path("/getReceptList")
	//	@Produces(MediaType.APPLICATION_JSON)
	//	public List<ReceptDTO> getReceptList() {
	//		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());
	//		List<ReceptDTO> recList = null;
	//
	//		try {
	//			recList = receptDAO.getReceptList();
	//		} catch (DALException e) {
	//			e.printStackTrace();
	//		}
	//		return recList;
	//	}
	//
	//	@DELETE
	//	@Path("/delReceptById")
	//	@Produces(MediaType.TEXT_PLAIN)
	//	public String delReceptById(@QueryParam("receptId") String receptId) {
	//		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());
	//
	//		try {
	//			receptDAO.deleteRecept(Integer.parseInt(receptId));
	//			return "Recept with id: " + receptId + "deleted!";
	//		} catch (DALException e) {
	//			e.printStackTrace();
	//		}
	//		return "Error deleting recept - id: " + receptId;
	//	}
	//
	//	@POST
	//	@Path("/createRecept")
	//	@Produces(MediaType.TEXT_PLAIN)
	//	@Consumes(MediaType.APPLICATION_JSON)
	//	public ReceptDTO createRecept(ReceptDTO receptDTO) {
	//		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());
	//
	//		try {
	//			receptDAO.createRecept(receptDTO);
	//		} catch (DALException e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}
	//
	//	@PUT
	//	@Path("/updateRecept")
	//	@Consumes(MediaType.APPLICATION_JSON)
	//	public Response updateRecept(ReceptDTO receptDTO) {
	//		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());
	//
	//		try {
	//			receptDAO.updateRecept(receptDTO);
	//			return Response.status(200).entity("Recept with id: " + receptDTO.getReceptId() + "name: " + receptDTO.getReceptName() + " - Updated").build();
	//		} catch (DALException e) {
	//			e.printStackTrace();
	//		}
	//		return Response.status(400).entity("Error updating recept - id: " + receptDTO.getReceptId()).build();
	//	}

}