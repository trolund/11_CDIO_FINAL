package final_cdio_11.RESTResources.model;

public class EditUserFormPOJO {
	
	private int oprId;
	private String oprFirstName;
	private String oprLastName;
	private String oprIni;
	private String oprEmail;
	private String oprCpr;
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
	public boolean isAdminRole() {
		return adminRole;
	}
	public void setAdminRole(boolean adminRole) {
		this.adminRole = adminRole;
	}
	public boolean isFarmaceutRole() {
		return farmaceutRole;
	}
	public void setFarmaceutRole(boolean farmaceutRole) {
		this.farmaceutRole = farmaceutRole;
	}
	public boolean isVaerkRole() {
		return vaerkRole;
	}
	public void setVaerkRole(boolean vaerkRole) {
		this.vaerkRole = vaerkRole;
	}
	public boolean isLaborantRole() {
		return laborantRole;
	}
	public void setLaborantRole(boolean laborantRole) {
		this.laborantRole = laborantRole;
	}
	private int status;
	private boolean adminRole, farmaceutRole, vaerkRole, laborantRole;

}
