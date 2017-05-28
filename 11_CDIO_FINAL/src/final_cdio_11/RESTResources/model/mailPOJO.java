package final_cdio_11.RESTResources.model;

public class mailPOJO {
	
	private String toMail, subject, msg;

	public mailPOJO(String toMail, String subject, String msg) {
		super();
		this.toMail = toMail;
		this.subject = subject;
		this.msg = msg;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
