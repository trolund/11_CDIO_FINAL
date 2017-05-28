package final_cdio_11.RESTResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import final_cdio_11.RESTResources.model.mailPOJO;
import final_cdio_11.java.utils.SendEmail;

@Path("/mail")
public class RESTMail {
	
	SendEmail udbringer = new SendEmail();
	
	@POST
	@Path("/newPassword")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String newPasswordMail(mailPOJO mailData) {

		udbringer.mail(mailData.getToMail(), mailData.getSubject(), mailData.getMsg());
		
		return "Mail send";
	}

	

}
