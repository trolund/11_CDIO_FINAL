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
import final_cdio_11.RESTResources.model.EditUserFormPOJO;
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
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devUserDeletedSuccessMessage(id));
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devUserDeletedFailureMessage(id));
			return false;
		} catch (NumberFormatException e) {
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devUserDeletedFailureMessage(id));
			e.printStackTrace();
			return false;
		}
	}

	@POST
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String verify(LoginFormPOJO loginFormData) throws JsonParseException, JsonMappingException, IOException {
		int oprId = loginFormData.getOprId();
		String password = loginFormData.getPassword();
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		if (utils.DEV_ENABLED) utils.logMessage(textHandler.devUserLoginMessage(oprId, password));

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

		if (utils.sha256(password).equals(oprDTO.getOprPassword())) {
			return textHandler.succLoggedIn;
		} else {
			return textHandler.errInvalidCredentials;
		}
	}

	@POST
	@Path("/addopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String addOpr(CreateUserFormPOJO createUserFormData) throws DALException {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		SQLRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());

		OperatorDTO oprDTO = new OperatorDTO(createUserFormData.getOprId(), createUserFormData.getOprFirstName(), createUserFormData.getOprLastName(), createUserFormData.getOprIni(), createUserFormData.getOprEmail(), createUserFormData.getOprCpr(), createUserFormData.getOprPassword(), createUserFormData.getStatus());

		try {
			oprDAO.createOperator(oprDTO);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedUser(createUserFormData.getOprId()));

			if (createUserFormData.isAdminRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_ADMIN, 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_ADMIN));
			}

			if (createUserFormData.isFarmaceutRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_FARMACEUT, 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_FARMACEUT));
			}

			if (createUserFormData.isVaerkRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_VAERK, 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_VAERK));
			}

			if (createUserFormData.isLaborantRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_LABORANT, 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_LABORANT));
			}

			return textHandler.succAddedUser(createUserFormData.getOprId());
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errUserCreation;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return textHandler.errUnknownFailure; // skulle vi ikke heller have en response her?
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
				CreateUserFormPOJO createUserFormPOJO = new CreateUserFormPOJO();
				createUserFormPOJO.setOprId(oprDTO.getOprId());
				createUserFormPOJO.setOprIni(oprDTO.getOprIni());
				createUserFormPOJO.setOprCpr(oprDTO.getOprCpr());
				createUserFormPOJO.setOprPassword(oprDTO.getOprPassword());
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
	@Path("/getOprRoleList/{oprId}")
	@Produces(MediaType.TEXT_HTML)
	public String getOprRoleList(@PathParam("oprId") String oprId) {
		SQLRoleDAO oprDAO = new SQLRoleDAO(Connector.getInstance());
		StringBuilder returnString = new StringBuilder();
		List<RoleDTO> oprRoleList = null;

		try {
			oprRoleList = oprDAO.getOprRoles(Integer.parseInt(oprId));
		} catch (DALException e) {
			e.printStackTrace();
		}

		if (oprRoleList == null) return "None.";

		for (Iterator<RoleDTO> iterator = oprRoleList.iterator(); iterator.hasNext();) {
			RoleDTO roleDTO = (RoleDTO) iterator.next();
			if (iterator.hasNext()) returnString.append(roleDTO.getRoleName() + ", ");
			else returnString.append(roleDTO.getRoleName() + ".");
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
			if (utils.DEV_ENABLED) utils.logMessage("RoleList successfully created. Trying to return..");
			return oprRoleList;
		} catch (DALException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/updateopr")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateOpr(EditUserFormPOJO editUserFormData) throws DALException {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		SQLRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());

		OperatorDTO oprDTO = new OperatorDTO(editUserFormData.getOprId(), editUserFormData.getOprFirstName(), editUserFormData.getOprLastName(), editUserFormData.getOprIni(), editUserFormData.getOprEmail(), editUserFormData.getOprCpr(), oprDAO.getOperator(editUserFormData.getOprId()).getOprPassword(), editUserFormData.getStatus());

		try {
			oprDAO.updateOperator(oprDTO);
			//			if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedUser(editUserFormData.getOprId()));
			//
			//			if (editUserFormData.isAdminRole()) {
			//				roleDAO.createRole(new RoleDTO(editUserFormData.getOprId(), textHandler.ROLE_ADMIN, 0));
			//				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(editUserFormData.getOprId(), textHandler.ROLE_ADMIN));
			//			}
			//
			//			if (createUserFormData.isFarmaceutRole()) {
			//				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_FARMACEUT, 0));
			//				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_FARMACEUT));
			//			}
			//
			//			if (createUserFormData.isVaerkRole()) {
			//				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_VAERK, 0));
			//				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_VAERK));
			//			}
			//
			//			if (createUserFormData.isLaborantRole()) {
			//				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), textHandler.ROLE_LABORANT, 0));
			//				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), textHandler.ROLE_LABORANT));
			//			}

			return textHandler.succAddedUser(editUserFormData.getOprId());
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errUserCreation;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return textHandler.errUnknownFailure; // skulle vi ikke heller have en response her?
	}
}