package final_cdio_11.RESTResources;

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
	public ReceptDTO getReceptById(@QueryParam("id") String id){
		
		SQLReceptDAO dao = new SQLReceptDAO(Connector.getInstance());
		
		try {
			return dao.getRecept(Integer.parseInt(id));
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	@DELETE 
	@Path("/DELById")
	@Produces(MediaType.TEXT_PLAIN)
	public String DELReceptById(@QueryParam("id") String id){
		
		SQLReceptDAO dao = new SQLReceptDAO(Connector.getInstance());
		
		try {
			dao.deleteRecept(Integer.parseInt(id));
			return "Recept with id: " + id + "deletet!";
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return "Error deleting recept - id: " + id;	
	}
	
	@POST
	@Path("/newRecept")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReceptDTO newRecept(ReceptDTO obj){
		
		SQLReceptDAO dao = new SQLReceptDAO(Connector.getInstance());
		
		try {
			dao.createRecept(obj);;
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	@PUT
	@Path("/updateRecept")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRecept(ReceptDTO obj){
		
		SQLReceptDAO dao = new SQLReceptDAO(Connector.getInstance());
		
		try {
			dao.updateRecept(obj);
			return Response.status(200).entity("Recept with id: " + obj.getReceptId() + "name: " + obj.getReceptName() + " - Updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return Response.status(400).entity("Error updating recept - id: " + obj.getReceptId()).build();		
	}
	
	

}
