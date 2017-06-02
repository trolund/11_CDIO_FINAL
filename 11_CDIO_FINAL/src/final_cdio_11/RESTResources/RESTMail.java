package final_cdio_11.RESTResources;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import final_cdio_11.RESTResources.model.MailPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.utils.SendEmail;
import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;

@Path("/mail")
public class RESTMail {
	
	private final TextHandler textHandler = TextHandler.getInstance();
	private final Utils utils = Utils.getInstance();
	
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

	@POST
	@Path("/newPass/{oprId}")
	public Response mail(@PathParam("oprId") String oprId) {
		SendEmail mailobj = new SendEmail();
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		
		String newPass = utils.generatePassword();
	
		try {
			OperatorDTO oprDTO = oprDAO.getOperator(Integer.parseInt(oprId));
			
			oprDTO.setOprPassword(newPass);
			
			oprDAO.updateOperator(oprDTO);
			
			mailobj.sendMail(oprDTO.getOprEmail(), textHandler.mailMessage(oprDTO), "New Password");
			
			utils.logMessage("Mail message: '" + textHandler.mailMessage(oprDTO) + "'");
			
			return Response.status(200).entity("Mail sent").build();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return Response.status(400).entity("Mail failed").build();
	}
	
}