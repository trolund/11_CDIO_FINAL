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
	private String oprRole1, oprRole2, oprRole3, oprRole4;

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

	public String getOprRole1() {
		return oprRole1;
	}

	public void setOprRole1(String oprRole1) {
		this.oprRole1 = oprRole1;
	}

	public String getOprRole2() {
		return oprRole2;
	}

	public void setOprRole2(String oprRole2) {
		this.oprRole2 = oprRole2;
	}

	public String getOprRole3() {
		return oprRole3;
	}

	public void setOprRole3(String oprRole3) {
		this.oprRole3 = oprRole3;
	}

	public String getOprRole4() {
		return oprRole4;
	}

	public void setOprRole4(String oprRole4) {
		this.oprRole4 = oprRole4;
	}

	public List<String> getOprRole() {
		List<String> list = new ArrayList<>();

		if (oprRole1 != "None") {
			list.add(oprRole1);
		}

		if (oprRole2 != "None") {
			list.add(oprRole2);
		}

		if (oprRole3 != "None") {
			list.add(oprRole3);
		}

		if (oprRole4 != "None") {
			list.add(oprRole4);
		}

		return list;
	}

}