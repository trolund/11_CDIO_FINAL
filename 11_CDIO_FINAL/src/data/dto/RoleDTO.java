package data.dto;

/*
 * Role data transfer object.
 * This class is used to store information about database entities.
 */
public class RoleDTO {

	private int oprId;
	private String roleName;

	public RoleDTO(int oprId, String roleName) {
		this.oprId = oprId;
		this.roleName = roleName;
	}

	public int getOprId() {
		return oprId;
	}

	public String getRoleName() {
		return roleName;
	}

	@Override
	public String toString() {
		return "RoleDTO [" + oprId + ", " + roleName + "]";
	}

}