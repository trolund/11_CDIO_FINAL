package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.Role;
import final_cdio_11.java.data.dto.RoleDTO;

public class RoleValidator implements IRoleValidator {

	@Override
	public boolean isOprIdValid(int oprId) {
		return oprId >= 1 && oprId <= 99999999;
	}

	@Override
	public boolean isRoleNameValid(String roleName) {
		return roleName.equals(Role.Admin.toString()) || roleName.equals(Role.Farmaceut.toString()) || roleName.equals(Role.Værkfører.toString()) || roleName.equals(Role.Laborant.toString());
	}

	@Override
	public boolean isRoleStatusValid(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isRoleValid(RoleDTO roleDTO) {
		return false;
	}

}