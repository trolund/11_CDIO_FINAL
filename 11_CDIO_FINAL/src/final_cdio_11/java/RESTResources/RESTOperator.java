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

import final_cdio_11.java.RESTResources.controller.IOperatorController;
import final_cdio_11.java.RESTResources.controller.OperatorController;
import final_cdio_11.java.RESTResources.pojo.CreateUserFormPOJO;
import final_cdio_11.java.RESTResources.pojo.EditUserFormPOJO;
import final_cdio_11.java.RESTResources.pojo.LoginFormPOJO;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;

/*
 * RESTResource to handle operator related actions.
 */
@Path("/opr")
public class RESTOperator {

	private final IOperatorController oprController = new OperatorController();

	/* Operator deletion */
	@POST
	@Path("/deleteOpr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean deleteOperator(String oprId) {
		return oprController.deleteOperator(oprId);
	}

	/* Operator password verification */
	@POST
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String verifyOperatorLogin(LoginFormPOJO loginFormData) throws JsonParseException, JsonMappingException, IOException {
		return oprController.verifyOperatorLogin(loginFormData);
	}

	/* Operator creation */
	@POST
	@Path("/addopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createOperator(CreateUserFormPOJO createUserFormData) throws DALException {
		return oprController.createOperator(createUserFormData);
	}

	/* OperatorPOJO */
	@GET
	@Path("/{oprId}")
	@Produces(MediaType.APPLICATION_JSON)
	public OperatorDTO createOperatorPOJO(@PathParam("oprId") String oprId) {
		return oprController.createOperatorPOJO(oprId);
	}

	/* Operator list */
	@GET
	@Path("/getOprList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OperatorDTO> getOperatorList() {
		return oprController.getOperatorList();
	}

	/* Operator roles from id */
	@GET
	@Path("/getOprRoleList/{oprId}")
	@Produces(MediaType.TEXT_HTML)
	public String getOperatorRolesAsString(@PathParam("oprId") String oprId) {
		return oprController.getOperatorRolesAsString(oprId);
	}

	/* Operator roleList from id */
	@GET
	@Path("/RoleList/{oprId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleDTO> getOperatorRoleListJSON(@PathParam("oprId") String oprId) {
		System.out.println(oprId);
		List<RoleDTO> operatorRoleList = oprController.getOperatorRoleList(oprId);
		System.out.println(operatorRoleList);
		return operatorRoleList;
	}

	/* Operator update */
	@POST
	@Path("/updateopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateOperator(EditUserFormPOJO editUserFormData) throws DALException {
		return oprController.updateOperator(editUserFormData);
	}

}