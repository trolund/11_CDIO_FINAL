package RESTResources.model;

import java.util.ArrayList;
import java.util.List;

public class creatUserFormPOJO {

	int oprId;
	String oprName;
	String oprIni;
	String oprCpr;
	String oprPassword;
	String oprRole1;
	String oprRole2;
	String oprRole3;
	String oprRole4;

	public int getOprId() {
		return oprId;
	}

	public void setOprId(int oprId) {
		this.oprId = oprId;
	}

	public String getOprName() {
		return oprName;
	}

	public void setOprName(String oprName) {
		this.oprName = oprName;
	}

	public String getOprIni() {
		return oprIni;
	}

	public void setOprIni(String oprIni) {
		this.oprIni = oprIni;
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

	public List<String> getOprRole() {
		List<String> liste = new ArrayList<>();

		if (oprRole1 != "None") {
			liste.add(oprRole1);
		}

		if (oprRole2 != "None") {
			liste.add(oprRole2);
		}

		if (oprRole3 != "None") {
			liste.add(oprRole3);
		}

		if (oprRole4 != "None") {
			liste.add(oprRole4);
		}

		return liste;
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

}