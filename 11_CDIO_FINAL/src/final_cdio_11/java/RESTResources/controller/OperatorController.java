package final_cdio_11.java.RESTResources.controller;

import java.util.Iterator;
import java.util.List;

import final_cdio_11.java.RESTResources.pojo.CreateUserFormPOJO;
import final_cdio_11.java.RESTResources.pojo.EditUserFormPOJO;
import final_cdio_11.java.RESTResources.pojo.LoginFormPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dao.IOperatorDAO;
import final_cdio_11.java.data.dao.IRoleDAO;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dao.SQLRoleDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;
import final_cdio_11.java.data.validator.IOperatorValidator;
import final_cdio_11.java.data.validator.OperatorValidator;
import final_cdio_11.java.handler.TextHandler;
import final_cdio_11.java.utils.Utils;

/*
 * REST Controller for business logic associated with Operator related utilities.
 */
public class OperatorController implements IOperatorController {

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();
	private IOperatorValidator oprValidator = new OperatorValidator();

	/* Gets an Operator list */
	@Override
	public List<OperatorDTO> getOperatorList() {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		List<OperatorDTO> oprList = null;

		try {
			oprList = oprDAO.getOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return oprList;
	}

	/* Concatenates all roles to a single String and returns it. */
	@Override
	public String getOperatorRolesAsString(String oprId) {
		IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());
		StringBuilder returnString = new StringBuilder();
		List<RoleDTO> oprRoleList = null;

		try {
			oprRoleList = roleDAO.getOprRoles(Integer.parseInt(oprId));
		} catch (DALException e) {
			e.printStackTrace();
		}

		if (oprRoleList == null) {
			return "None.";
		}

		for (Iterator<RoleDTO> iterator = oprRoleList.iterator(); iterator.hasNext();) {
			RoleDTO roleDTO = (RoleDTO) iterator.next();
			if (iterator.hasNext()) returnString.append(roleDTO.getRoleName() + ", ");
			else returnString.append(roleDTO.getRoleName() + ".");
		}
		return returnString.toString();
	}

	/* Returns a List containing the roles of a specific operator */
	@Override
	public List<RoleDTO> getOperatorRoleList(String oprId) {
		IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());
		List<RoleDTO> oprRoleList = null;

		try {
			oprRoleList = roleDAO.getOprRoles(Integer.parseInt(oprId));
			return oprRoleList;
		} catch (DALException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return null;
	}

	/* Verifies an Operators id and password. */
	@Override
	public String verifyOperatorLogin(LoginFormPOJO loginFormData) {
		int oprId = loginFormData.getOprId();
		String password = loginFormData.getPassword();
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

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
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.succLoginMessage(oprId, password));
			return textHandler.succLoggedIn;
		} else {
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.errLoginFailed(oprId));
			return textHandler.errInvalidCredentials;
		}
	}

	/* Create an Operator in the underlying data layer. */
	@Override
	public String createOperator(CreateUserFormPOJO createUserFormData) {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());

		OperatorDTO oprDTO = new OperatorDTO(createUserFormData.getOprId(), createUserFormData.getOprFirstName(), createUserFormData.getOprLastName(), createUserFormData.getOprIni(), createUserFormData.getOprEmail(), createUserFormData.getOprCpr(), createUserFormData.getOprPassword(), createUserFormData.getStatus());

		if (!oprValidator.isOprValid(oprDTO)) return textHandler.errOprInvalid;

		try {
			oprDAO.createOperator(oprDTO);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.succOprAdd(createUserFormData.getOprId()));

			if (createUserFormData.isAdminRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Admin.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(createUserFormData.getOprId(), Role.Admin.toString()));
			}

			if (createUserFormData.isFarmaceutRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Farmaceut.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(createUserFormData.getOprId(), Role.Farmaceut.toString()));
			}

			if (createUserFormData.isVærkførerRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Værkfører.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(createUserFormData.getOprId(), Role.Værkfører.toString()));
			}

			if (createUserFormData.isLaborantRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Laborant.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(createUserFormData.getOprId(), Role.Laborant.toString()));
			}

			return textHandler.succOprAdd(createUserFormData.getOprId());
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errOprCreation;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return textHandler.errOprCreate; // skulle vi ikke heller have en response her?
	}

	/* Update an Operator in the underlying data layer. */
	@Override
	public String updateOperator(EditUserFormPOJO editUserFormData) {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());

		OperatorDTO oprDTO = null;
		try {
			oprDTO = new OperatorDTO(editUserFormData.getOprId(), editUserFormData.getOprFirstName(), editUserFormData.getOprLastName(), editUserFormData.getOprIni(), editUserFormData.getOprEmail(), editUserFormData.getOprCpr(), oprDAO.getOperator(editUserFormData.getOprId()).getOprPassword(), editUserFormData.getStatus());
		} catch (DALException e) {
			e.printStackTrace();
		}

		if (!oprValidator.isOprValid(oprDTO)) return textHandler.errOprInvalid;

		try {
			oprDAO.updateOperator(oprDTO);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.succOprUpdate(oprDTO.getOprId()));

			if (editUserFormData.isAdminRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Admin.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(editUserFormData.getOprId(), Role.Admin.toString()));
			}

			if (editUserFormData.isFarmaceutRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Farmaceut.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(editUserFormData.getOprId(), Role.Farmaceut.toString()));
			}

			if (editUserFormData.isVaerkforerRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Værkfører.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(editUserFormData.getOprId(), Role.Værkfører.toString()));
			}

			if (editUserFormData.isLaborantRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Laborant.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succRoleAdd(editUserFormData.getOprId(), Role.Laborant.toString()));
			}

			return textHandler.succOprUpdate(oprDTO.getOprId());
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errOprCreation;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return textHandler.errOprUpdate; // skulle vi ikke heller have en response her?
	}

	/* Create an Operator in the underlying data layer. */
	@Override
	public boolean deleteOperator(String oprId) {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		int id = -1;

		try {
			id = Integer.parseInt(oprId);
			oprDAO.deleteOperator(id);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devOprDeletedSuccessMessage(id));
			return true;
		} catch (DALException e) {
			e.printStackTrace();
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devOprDeletedFailureMessage(id));
			return false;
		} catch (NumberFormatException e) {
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devOprDeletedFailureMessage(id));
			e.printStackTrace();
			return false;
		}
	}

	/* Create an OperatorPOJO. */
	@Override
	public OperatorDTO createOperatorPOJO(String oprId) {

		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		int id = Integer.parseInt(oprId);

		OperatorDTO oprDTO = null;
		try {
			oprDTO = oprDAO.getOperator(id);
		} catch (DALException e) {
			e.printStackTrace();
		}

		CreateUserFormPOJO createUserFormPOJO = new CreateUserFormPOJO();
		createUserFormPOJO.setOprId(oprDTO.getOprId());
		createUserFormPOJO.setOprIni(oprDTO.getOprIni());
		createUserFormPOJO.setOprCpr(oprDTO.getOprCpr());
		createUserFormPOJO.setOprPassword(oprDTO.getOprPassword());
		return oprDTO;
	}

}