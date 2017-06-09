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
import final_cdio_11.java.handler.TextHandler;
import final_cdio_11.java.utils.Utils;

public class OperatorController implements IOperatorController {

	private final Utils utils = Utils.getInstance();
	private final TextHandler textHandler = TextHandler.getInstance();

	@Override
	public List<OperatorDTO> getOperatorList() {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		List<OperatorDTO> oprList = null;

		try {
			oprList = oprDAO.getOperatorList();
			if (utils.DEV_ENABLED) utils.logMessage("Returning operator list.");
		} catch (DALException e) {
			e.printStackTrace();
		}
		return oprList;
	}

	/* 
	 * Denne metode er grunden til, at loading går så langsomt på hjemmesiden. 
	 * Den tager sindssyg lang tid. 
	 * Det ville være en god ide, hvis vi kunne gemme alle rollerne som en String, 
	 * i en operator DTO. Så ville det køre i konstant tid, og ikke linear tid. 
	 */
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
			if (utils.DEV_ENABLED) utils.logMessage("User [" + oprId + "] does not have any roles. Setting to 'None.'.");
			return "None.";
		}

		if (utils.DEV_ENABLED) utils.logMessage("Concatenating operator roles to String.");
		for (Iterator<RoleDTO> iterator = oprRoleList.iterator(); iterator.hasNext();) {
			RoleDTO roleDTO = (RoleDTO) iterator.next();
			if (iterator.hasNext()) returnString.append(roleDTO.getRoleName() + ", ");
			else returnString.append(roleDTO.getRoleName() + ".");
		}
		return returnString.toString();
	}

	@Override
	public List<RoleDTO> getOperatorRoleList(String OprId) {
		System.out.println(OprId);
		IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());
		List<RoleDTO> oprRoleList = null;

		try {
			oprRoleList = roleDAO.getOprRoles(Integer.parseInt(OprId));
			if (utils.DEV_ENABLED) utils.logMessage("RoleList successfully created. Trying to return.");
			return oprRoleList;
		} catch (DALException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return null;
	}

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
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.devUserLoginMessage(oprId, password));
			return textHandler.succLoggedIn;
		} else {
			if (utils.DEV_ENABLED) utils.logMessage("User [" + oprId + ":" + password + "] failed to log in.");
			return textHandler.errInvalidCredentials;
		}
	}

	@Override
	public String createOperator(CreateUserFormPOJO createUserFormData) {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		IRoleDAO roleDAO = new SQLRoleDAO(Connector.getInstance());

		OperatorDTO oprDTO = new OperatorDTO(createUserFormData.getOprId(), createUserFormData.getOprFirstName(), createUserFormData.getOprLastName(), createUserFormData.getOprIni(), createUserFormData.getOprEmail(), createUserFormData.getOprCpr(), createUserFormData.getOprPassword(), createUserFormData.getStatus());

		// VALIDATE HER :)
		
		try {
			oprDAO.createOperator(oprDTO);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedUser(createUserFormData.getOprId()));

			if (createUserFormData.isAdminRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Admin.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), Role.Admin.toString()));
			}

			if (createUserFormData.isFarmaceutRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Farmaceut.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), Role.Farmaceut.toString()));
			}

			if (createUserFormData.isVærkførerRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Værkfører.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), Role.Værkfører.toString()));
			}

			if (createUserFormData.isLaborantRole()) {
				roleDAO.createRole(new RoleDTO(createUserFormData.getOprId(), Role.Laborant.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(createUserFormData.getOprId(), Role.Laborant.toString()));
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

		try {
			oprDAO.updateOperator(oprDTO);
			if (utils.DEV_ENABLED) utils.logMessage("Updating user: " + oprDTO);

			if (editUserFormData.isAdminRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Admin.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(editUserFormData.getOprId(), Role.Admin.toString()));
			}

			if (editUserFormData.isFarmaceutRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Farmaceut.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(editUserFormData.getOprId(), Role.Farmaceut.toString()));
			}

			if (editUserFormData.isVaerkforerRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Værkfører.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(editUserFormData.getOprId(), Role.Værkfører.toString()));
			}

			if (editUserFormData.isLaborantRole()) {
				roleDAO.updateRole(new RoleDTO(editUserFormData.getOprId(), Role.Laborant.toString(), 0));
				if (utils.DEV_ENABLED) utils.logMessage(textHandler.succAddedRole(editUserFormData.getOprId(), Role.Laborant.toString()));
			}

			return textHandler.succUpdateUser(oprDTO.getOprId());
		} catch (DALException e) {
			e.printStackTrace();
			return textHandler.errUserCreation;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return textHandler.errUnknownFailure; // skulle vi ikke heller have en response her?
	}

	@Override
	public boolean deleteOperator(String oprId) {
		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
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

	@Override
	public OperatorDTO createOperatorPOJO(String oprId) {
		
		System.out.println("createOprPojo ID String: " + oprId);
		
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
		
		
//		IOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
//
//		int id = Integer.parseInt(oprId);
//		List<OperatorDTO> oprList = null;
//
//		try {
//			oprList = oprDAO.getOperatorList();
//		} catch (DALException e) {
//			e.printStackTrace();
//		}
//
//		for (OperatorDTO oprDTO : oprList) {
//			if (oprDTO.getOprId() == id) {
//				CreateUserFormPOJO createUserFormPOJO = new CreateUserFormPOJO();
//				createUserFormPOJO.setOprId(oprDTO.getOprId());
//				createUserFormPOJO.setOprIni(oprDTO.getOprIni());
//				createUserFormPOJO.setOprCpr(oprDTO.getOprCpr());
//				createUserFormPOJO.setOprPassword(oprDTO.getOprPassword());
//				return oprDTO;
//			}
//		}
////		return null;
	}

}