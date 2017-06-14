package final_cdio_11.java.data.dto.view;

/*
 * VUserTable data transfer object.
 * This class is used to store information about database entities.
 */
public class VUserTableDTO {

	private int status;
	private int oprId;
	private String oprFirstName;
	private String oprLastName;
	private String oprIni;
	private String oprEmail;
	private String oprCpr;
	private String oprRoles;

	public VUserTableDTO(int status, int oprId, String oprFirstName, String oprLastName, String oprIni, String oprEmail, String oprCpr, String oprRoles) {
		this.status = status;
		this.oprId = oprId;
		this.oprFirstName = oprFirstName;
		this.oprLastName = oprLastName;
		this.oprIni = oprIni;
		this.oprEmail = oprEmail;
		this.oprCpr = oprCpr;
		this.oprRoles = oprRoles;
	}

	public int getStatus() {
		return status;
	}

	public int getOprId() {
		return oprId;
	}

	public String getOprFirstName() {
		return oprFirstName;
	}

	public String getOprLastName() {
		return oprLastName;
	}

	public String getOprIni() {
		return oprIni;
	}

	public String getOprEmail() {
		return oprEmail;
	}

	public String getOprCpr() {
		return oprCpr;
	}

	public String getOprRoles() {
		return oprRoles;
	}

	@Override
	public String toString() {
		return "VUserTable [status=" + status + ", oprId=" + oprId + ", oprFirstName=" + oprFirstName + ", oprLastName=" + oprLastName + ", oprIni=" + oprIni + ", oprEmail=" + oprEmail + ", oprCpr=" + oprCpr + ", oprRoles=" + oprRoles + "]";
	}

}