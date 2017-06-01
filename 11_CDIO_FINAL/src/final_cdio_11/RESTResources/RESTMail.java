package final_cdio_11.RESTResources;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.RESTResources.model.MailPOJO;
import final_cdio_11.java.utils.SendEmail;

@Path("/mail")
public class RESTMail {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mail(MailPOJO mailData) {
		SendEmail mailobj = new SendEmail();

		try {
			mailobj.sendMail(mailData.getTo(), mailData.getMsg(), mailData.getSub());
			return Response.status(200).entity("Mail sent").build();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return Response.status(400).entity("Mail failed").build();
	}
	
}