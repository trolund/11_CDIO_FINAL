package final_cdio_11.RESTResources.model;

import java.util.ArrayList;
import java.util.List;

public class CreateUserFormPOJO {

	private int oprId;
	private String oprFirstName;
	private String oprLastName;
	private String oprIni;
	private String oprEmail;
	private String oprCpr;
	private String oprPassword;
	private int status;
	private String AdminRole;
	private String FarRole;
	private String VeakRole;
	private String LabRole;
	
	public String getAdminRole() {
		return AdminRole;
	}

	public void setAdminRole(String adminRole) {
		AdminRole = adminRole;
	}

	public String getFarRole() {
		return FarRole;
	}

	public void setFarRole(String farRole) {
		FarRole = farRole;
	}

	public String getVeakRole() {
		return VeakRole;
	}

	public void setVeakRole(String veakRole) {
		VeakRole = veakRole;
	}

	public String getLabRole() {
		return LabRole;
	}

	public void setLabRole(String labRole) {
		LabRole = labRole;
	}

	public List<String> getRoles(){
		List<String> roles = new ArrayList<String>(); 
		
		if(AdminRole == "1"){
			roles.add("Admin");
		}
		if(FarRole == "1"){
			roles.add("Farmaceut");
		}
		if(VeakRole == "1"){
			roles.add("Værkfører");
		}
		if(LabRole == "1"){
			roles.add("Laborant");
		}
		return roles;
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

	public void setOprFirstName(String oprName) {
		this.oprFirstName = oprName;
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

	public String getOprPassword() {
		return oprPassword;
	}

	public void setOprPassword(String oprPassword) {
		this.oprPassword = oprPassword;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


}