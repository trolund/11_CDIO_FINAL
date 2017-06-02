package final_cdio_11.java.RESTResources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.controller.IMailController;
import final_cdio_11.java.RESTResources.controller.MailController;
import final_cdio_11.java.RESTResources.pojo.MailPOJO;

@Path("/mail")
public class RESTMail {

	private final IMailController mailController = new MailController();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response sendMail(MailPOJO mailPOJO) {
		return mailController.sendMail(mailPOJO);
	}

	@POST
	@Path("/newPass/{oprId}")
	public Response sendNewPassword(@PathParam("oprId") String oprId) {
		return mailController.sendNewPassword(oprId);
	}

}