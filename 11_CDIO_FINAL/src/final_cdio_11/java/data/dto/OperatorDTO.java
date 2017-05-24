package final_cdio_11.java.data.dto;

/*
 * Operator data transfer object.
 * This class is used to store information about database entities.
 */
public class OperatorDTO {

	private int oprId;
	private String oprName;
	private String oprLastName;
	private String oprIni;
	private String oprEmail;
	private String oprCpr;
	private String oprPassword;
	private int status;

	public OperatorDTO(int oprId, String oprName, String oprLastName, String oprIni, String oprEmail, String oprCpr, String oprPassword, int status) {
		this.oprId = oprId;
		this.oprName = oprName;
		this.oprLastName = oprLastName;
		this.oprIni = oprIni;
		this.oprEmail = oprEmail;
		this.oprCpr = oprCpr;
		this.oprPassword = oprPassword;
		this.status = status;
	}

	public int getOprId() {
		return oprId;
	}

	public String getOprName() {
		return oprName;
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

	public String getOprPassword() {
		return oprPassword;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "OperatorDTO [oprId=" + oprId + ", oprName=" + oprName + ", oprLastName=" + oprLastName + ", oprIni=" + oprIni + ", oprEmail=" + oprEmail + ", oprCpr=" + oprCpr + ", oprPassword=" + oprPassword + ", status=" + status + "]";
	}

}