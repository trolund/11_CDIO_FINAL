package final_cdio_11.java.RESTResources;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import final_cdio_11.java.data.DALException;
import final_cdio_11.java.utils.DbUtils;

@Path("/db")
public class RESTDbUtils {

	@GET
	@Path("/reset")
	public Response resetDatabase() {
		try {
			DbUtils.resetDatabase();
		} catch (DALException e) {
			e.printStackTrace();
			return Response.status(400).entity("Failed to reset database.").type("text/plain").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(400).entity("Failed to reset database.").type("text/plain").build();
		}
		return Response.status(200).entity("Database reset successfully.").type("text/plain").build();
	}

}