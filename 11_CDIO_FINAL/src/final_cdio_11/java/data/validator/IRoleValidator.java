package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RoleDTO;

/*
 * Interface for the role validator class.
 * This interface contains various validation methods
 * for the RoleDTO.
 */
public interface IRoleValidator {
	boolean isOprIdValid(int oprId);
	boolean isRoleNameValid(String roleName);
	boolean isRoleStatusValid(int status);
	boolean isRoleValid(RoleDTO roleDTO);
}