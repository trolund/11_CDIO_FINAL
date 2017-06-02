package final_cdio_11.java.RESTResources.pojo;

public class EditUserFormPOJO {
	
	private int oprId;
	private int status;
	private String oprFirstName;
	private String oprLastName;
	private String oprIni;
	private String oprEmail;
	private String oprCpr;
	private boolean AdminRole, FarRole, VeakRole, LabRole;
	
	
	public boolean isAdminRole() {
		return AdminRole;
	}
	public void setAdminRole(boolean AdminRole) {
		this.AdminRole = AdminRole;
	}
	public boolean isFarRole() {
		return FarRole;
	}
	public void setFarRole(boolean farRole) {
		FarRole = farRole;
	}
	public boolean isVeakRole() {
		return VeakRole;
	}
	public void setVeakRole(boolean veakRole) {
		VeakRole = veakRole;
	}
	public boolean isLabRole() {
		return LabRole;
	}
	public void setLabRole(boolean labRole) {
		LabRole = labRole;
	}
	public int getOprId() {
		return oprId;
	}
	public void setOprId(int oprId) {
		this.oprId = oprId;
	}
	public String getOprFirstName() {
		return oprFirstName;
	}
	public void setOprFirstName(String oprFirstName) {
		this.oprFirstName = oprFirstName;
	}
	public String getOprLastName() {
		return oprLastName;
	}
	public void setOprLastName(String oprLastName) {
		this.oprLastName = oprLastName;
	}
	public String getOprIni() {
		return oprIni;
	}
	public void setOprIni(String oprIni) {
		this.oprIni = oprIni;
	}
	public String getOprEmail() {
		return oprEmail;
	}
	public void setOprEmail(String oprEmail) {
		this.oprEmail = oprEmail;
	}
	public String getOprCpr() {
		return oprCpr;
	}
	public void setOprCpr(String oprCpr) {
		this.oprCpr = oprCpr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

}
