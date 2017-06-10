package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dto.RoleDTO;
import final_cdio_11.java.utils.Utils;

public class RoleValidator implements IRoleValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isOprIdValid(int oprId) {
		boolean isOprIdValid = oprId >= 1 && oprId <= 99999999;
		if (!isOprIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprIdValid");
		return isOprIdValid;
	}

	@Override
	public boolean isRoleNameValid(String roleName) {
		boolean isRoleNameValid = roleName.equals(Role.Admin.toString()) || roleName.equals(Role.Farmaceut.toString()) || roleName.equals(Role.Værkfører.toString()) || roleName.equals(Role.Laborant.toString());
		if (!isRoleNameValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRoleNameValid");
		return isRoleNameValid;
	}

	@Override
	public boolean isRoleStatusValid(int status) {
		boolean isRoleStatusValid = status == 0 || status == 1;
		if (!isRoleStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRoleStatusValid");
		return isRoleStatusValid;
	}

	@Override
	public boolean isRoleValid(RoleDTO roleDTO) {
		boolean isRoleValid = isOprIdValid(roleDTO.getOprId()) && isRoleNameValid(roleDTO.getRoleName()) && isRoleStatusValid(roleDTO.getStatus());
		return isRoleValid;
	}

}