package final_cdio_11.RESTResources;

import java.io.IOException;
import java.util.Iterator;
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

import final_cdio_11.RESTResources.model.CreateUserFormPOJO;
import final_cdio_11.RESTResources.model.LoginFormPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;
import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;

@Path("/opr")
public class RESTOperator {

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();

	@POST
	@Path("/deleteOpr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean delUser(String oprId) {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		int id = -1;

		try {
			id = Integer.parseInt(oprId);
			oprDAO.deleteOperator(id);

			if (utils.DEV_ENABLED) {
				utils.logMessage(textHandler.devUserDeletedSuccessMessage(id));
			}

			return true;
		} catch (DALException e) {
			e.printStackTrace();

			if (utils.DEV_ENABLED) {
				utils.logMessage(textHandler.devUserDeletedFailureMessage(id));
			}

			return false;
		} catch (NumberFormatException e) {

			if (utils.DEV_ENABLED) {
				utils.logMessage(textHandler.devUserDeletedFailureMessage(id));
			}

			e.printStackTrace();
			return false;
		}
	}

	@POST
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String verify(LoginFormPOJO formData) throws JsonParseException, JsonMappingException, IOException {
		int oprId = formData.getOprId();
		String password = formData.getPassword();

		if (utils.DEV_ENABLED) {
			utils.logMessage(textHandler.devUserLoginMessage(oprId, password));
		}

		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		Utils secUtil = Utils.getInstance();

		OperatorDTO oprDTO = null;
		try {
			oprDTO = oprDAO.getOperator(oprId);
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errIdDoesNotExist;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return textHandler.errIdInvalid;
		}

		if (secUtil.sha256(password).equals(oprDTO.getOprPassword())) {
			return textHandler.succLoggedIn;
		} else {
			return textHandler.errInvalidCredentials;
		}
	}

	@POST
	@Path("/addopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String Addopr(CreateUserFormPOJO formData) throws DALException {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		SQLRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());

		OperatorDTO oprDTO = new OperatorDTO(formData.getOprId(), formData.getOprName(), formData.getOprLastName(), formData.getOprIni(), formData.getOprEmail(), formData.getOprCpr(), formData.getOprPassword(), formData.getStatus());

		if (formData.getOprRole1().equals("None")) {
			RoleDTO roleDTO1 = new RoleDTO(formData.getOprId(), formData.getOprRole1(), formData.getStatus());
			roleDAO.createRole(roleDTO1);
		}

		if (formData.getOprRole2().equals("None")) {
			RoleDTO roleDTO2 = new RoleDTO(formData.getOprId(), formData.getOprRole2(), formData.getStatus());
			roleDAO.createRole(roleDTO2);
		}

		if (formData.getOprRole3().equals("None")) {
			RoleDTO roleDTO3 = new RoleDTO(formData.getOprId(), formData.getOprRole3(), formData.getStatus());
			roleDAO.createRole(roleDTO3);
		}

		if (formData.getOprRole4().equals("None")) {
			RoleDTO roleDTO4 = new RoleDTO(formData.getOprId(), formData.getOprRole4(), formData.getStatus());
			roleDAO.createRole(roleDTO4);
		}

		try {
			oprDAO.createOperator(oprDTO);

			if (utils.DEV_ENABLED) {
				utils.logMessage(textHandler.succAddedUser(formData.getOprId()));
			}

			return textHandler.succAddedUser(formData.getOprId());
		} catch (DALException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return textHandler.errFailure;
	}

	@GET
	@Path("/{oprId}")
	@Produces(MediaType.APPLICATION_JSON)
	public OperatorDTO getName(@PathParam("oprId") String oprId) {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		int id = Integer.parseInt(oprId);

		List<OperatorDTO> oprList = null;
		try {
			oprList = oprDAO.getOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		for (OperatorDTO oprDTO : oprList) {
			if (oprDTO.getOprId() == id) {
				CreateUserFormPOJO pojo = new CreateUserFormPOJO();
				try {
					List<RoleDTO> roleList = new SQLRoleDAO(Connector.getInstance()).getOprRoles(id);
					pojo.setOprId(oprDTO.getOprId());
					pojo.setOprIni(oprDTO.getOprIni());
					pojo.setOprCpr(oprDTO.getOprCpr());
					pojo.setOprPassword(oprDTO.getOprPassword());
					pojo.setOprRole1(roleList.get(0).getRoleName());
				} catch (DALException e) {
					e.printStackTrace();
				}
				return oprDTO;
			}
		}
		return null;
	}

	@GET
	@Path("/getOprList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OperatorDTO> getopr() {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		List<OperatorDTO> oprList = null;

		try {
			oprList = oprDAO.getOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return oprList;
	}

	@GET
	@Path("/getOprRoleList/{OprId}")
	@Produces(MediaType.TEXT_HTML)
	public String getoprRoleList(@PathParam("OprId") String OprId) {
		SQLRoleDAO oprDAO = new SQLRoleDAO(Connector.getInstance());

		StringBuilder returnString = new StringBuilder();
		List<RoleDTO> oprRoleList = null;

		try {
			oprRoleList = oprDAO.getOprRoles(Integer.parseInt(OprId));
		} catch (DALException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		for (Iterator<RoleDTO> iterator = oprRoleList.iterator(); iterator.hasNext();) {
			RoleDTO roleDTO = (RoleDTO) iterator.next();
			if (iterator.hasNext())
				returnString.append(roleDTO.getRoleName() + ", ");
			else
				returnString.append(roleDTO.getRoleName());
		}

		return returnString.toString();
	}

	@GET
	@Path("/RoleList/{OprId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleDTO> getoprRoleListJSON(@PathParam("OprId") String OprId) {
		SQLRoleDAO oprDAO = new SQLRoleDAO(Connector.getInstance());

		List<RoleDTO> oprRoleList = null;

		try {
			oprRoleList = oprDAO.getOprRoles(Integer.parseInt(OprId));
			return oprRoleList;
		} catch (DALException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return null;
	}

}