package final_cdio_11.java.data.dto.view;

public class VAdminOperatorDTO {

	private int oprId;
	private String oprName;
	private String oprIni;
	private String oprCpr;
	private String oprPassword;
	private String oprRoles;

	public VAdminOperatorDTO(int oprId, String oprName, String oprIni, String oprCpr, String oprPassword, String oprRoles) {
		this.oprId = oprId;
		this.oprName = oprName;
		this.oprIni = oprIni;
		this.oprCpr = oprCpr;
		this.oprPassword = oprPassword;
		this.oprRoles = oprRoles;
	}

	public int getOprId() {
		return oprId;
	}

	public String getOprName() {
		return oprName;
	}

	public String getOprIni() {
		return oprIni;
	}

	public String getOprCpr() {
		return oprCpr;
	}

	public String getOprPassword() {
		return oprPassword;
	}

	public String getOprRoles() {
		return oprRoles;
	}

	@Override
	public String toString() {
		return "VAdminOperatorDTO [oprId=" + oprId + ", oprName=" + oprName + ", oprIni=" + oprIni + ", oprCpr=" + oprCpr + ", oprPassword=" + oprPassword + ", oprRoles=" + oprRoles + "]";
	}

}