package final_cdio_11.java.RESTResources.pojo;

import java.util.ArrayList;
import java.util.List;

import final_cdio_11.java.handler.TextHandler;

public class CreateUserFormPOJO {

	private int oprId;
	private String oprFirstName;
	private String oprLastName;
	private String oprIni;
	private String oprEmail;
	private String oprCpr;
	private String oprPassword;
	private int status;
	private boolean adminRole, farmaceutRole, vaerkRole, laborantRole;

	private final TextHandler textHandler = TextHandler.getInstance();

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

	public void setLaborantRole(boolean labRole) {
		this.laborantRole = labRole;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<String>();
		if (adminRole) roles.add(textHandler.ROLE_ADMIN);
		if (farmaceutRole) roles.add(textHandler.ROLE_FARMACEUT);
		if (vaerkRole) roles.add(textHandler.ROLE_VAERK);
		if (laborantRole) roles.add(textHandler.ROLE_LABORANT);
		return roles;
	}

}