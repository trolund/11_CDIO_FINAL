package final_cdio_11.RESTResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dto.ReceptDTO;

@Path("/recept")
public class RESTRecept {
	
	@GET
	@Path("/getById")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public ReceptDTO getReceptById(@QueryParam("id") String id){
		
		SQLReceptDAO dao = new SQLReceptDAO(Connector.getInstance());
		
		try {
			return dao.getRecept(Integer.parseInt(id));
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}

}
