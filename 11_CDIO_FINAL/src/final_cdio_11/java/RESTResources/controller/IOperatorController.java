package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import final_cdio_11.java.RESTResources.pojo.CreateUserFormPOJO;
import final_cdio_11.java.RESTResources.pojo.EditUserFormPOJO;
import final_cdio_11.java.RESTResources.pojo.LoginFormPOJO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.data.dto.RoleDTO;

/*
 * Interface for the REST OperatorController.
 * This interface contains methods related to operators.
 */
public interface IOperatorController {
	List<OperatorDTO> getOperatorList();
	String getOperatorRolesAsString(String oprId);
	List<RoleDTO> getOperatorRoleList(String oprId);
	String verifyOperatorLogin(LoginFormPOJO loginFormData);
	String createOperator(CreateUserFormPOJO createUserFormData);
	String updateOperator(EditUserFormPOJO editUserFormData);
	boolean deleteOperator(String oprId);
	OperatorDTO createOperatorPOJO(String oprId);
	String updateOperatorNoRols(EditUserFormPOJO editUserFormData);
}