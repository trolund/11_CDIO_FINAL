package final_cdio_11.java.data.dto.view;

public class VForemanOperatorDTO {

	private int oprId;
	private String oprName;
	private String oprIni;
	private String oprCpr;
	private String oprRoles;

	public VForemanOperatorDTO(int oprId, String oprName, String oprIni, String oprCpr, String oprRoles) {
		this.oprId = oprId;
		this.oprName = oprName;
		this.oprIni = oprIni;
		this.oprCpr = oprCpr;
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

	public String getOprRoles() {
		return oprRoles;
	}

	@Override
	public String toString() {
		return "VForemanOperatorDTO [oprId=" + oprId + ", oprName=" + oprName + ", oprIni=" + oprIni + ", oprCpr=" + oprCpr + ", oprRoles=" + oprRoles + "]";
	}

}