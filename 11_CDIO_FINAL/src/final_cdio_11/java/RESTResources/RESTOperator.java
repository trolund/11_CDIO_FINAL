package final_cdio_11.java.RESTResources;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import final_cdio_11.java.RESTResources.controller.OperatorController;
import final_cdio_11.java.RESTResources.model.CreateUserFormPOJO;
import final_cdio_11.java.RESTResources.model.EditUserFormPOJO;
import final_cdio_11.java.RESTResources.model.LoginFormPOJO;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;

@Path("/opr")
public class RESTOperator {

	private final OperatorController con = new OperatorController();

	@POST
	@Path("/deleteOpr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean delUser(String oprId) {
		return con.delUser(oprId);
	}

	@POST
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String verify(LoginFormPOJO loginFormData) throws JsonParseException, JsonMappingException, IOException {
		return con.VerifyLogin(loginFormData);
	}

	@POST
	@Path("/addopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addOpr(CreateUserFormPOJO createUserFormData) throws DALException {
		return con.CreatUser(createUserFormData);
	}

	@GET
	@Path("/{oprId}")
	@Produces(MediaType.APPLICATION_JSON)
	public OperatorDTO getName(@PathParam("oprId") String oprId) {
		return con.getName(oprId);
	}

	@GET
	@Path("/getOprList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OperatorDTO> getopr() {
		return con.getopr();
	}

	@GET
	@Path("/getOprRoleList/{oprId}")
	@Produces(MediaType.TEXT_HTML)
	public String getOprRoleList(@PathParam("oprId") String oprId) {
		return con.getoprRoleList(oprId);
	}

	@GET
	@Path("/RoleList/{OprId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleDTO> getoprRoleListJSON(@PathParam("OprId") String OprId) {
		return con.getOprRoleList(OprId);
	}

	@POST
	@Path("/updateopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateOpr(EditUserFormPOJO editUserFormData) throws DALException {
		return con.updateOpr(editUserFormData);
	}

}