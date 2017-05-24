package final_cdio_11.java.data.dto;

/*
 * Role data transfer object.
 * This class is used to store information about database entities.
 */
public class RoleDTO {

	private int oprId;
	private String roleName;
	private int status;

	public RoleDTO(int oprId, String roleName, int status) {
		this.oprId = oprId;
		this.roleName = roleName;
		this.status = status;
	}

	public int getOprId() {
		return oprId;
	}

	public String getRoleName() {
		return roleName;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RoleDTO [oprId=" + oprId + ", roleName=" + roleName + ", status=" + status + "]";
	}

}