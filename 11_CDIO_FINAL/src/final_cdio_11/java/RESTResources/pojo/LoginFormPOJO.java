package final_cdio_11.java.RESTResources.pojo;

/*
 * POJO object to temporarily transfer data from data layer to web interface.
 */
public class LoginFormPOJO {

	private int oprId;
	private String password;

	public int getOprId() {
		return oprId;
	}

	public void setOprId(int oprId) {
		this.oprId = oprId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}