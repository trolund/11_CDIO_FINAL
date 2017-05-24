package data.dto;

/*
 * Operator data transfer object.
 * This class is used to store information about database entities.
 */
public class OperatorDTO {

	private int oprId;
	private String oprName;
	private String oprIni;
	private String oprCpr;
	private String oprPassword;

	public OperatorDTO(int oprId, String oprName, String oprIni, String oprCpr, String oprPassword) {
		this.oprId = oprId;
		this.oprName = oprName;
		this.oprIni = oprIni;
		this.oprCpr = oprCpr;
		this.oprPassword = oprPassword;
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

	@Override
	public String toString() {
		return "OperatorDTO [" + oprId + ", " + oprName + ", " + oprIni + ", " + oprCpr + ", " + oprPassword + "]";
	}

}