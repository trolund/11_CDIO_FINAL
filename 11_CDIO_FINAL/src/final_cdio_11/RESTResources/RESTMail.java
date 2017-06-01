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
import final_cdio_11.java.utils.Utils;

@Path("/mail")
public class RESTmail {

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
		Utils passGen = Utils.getInstance();
		
		String newPass = passGen.generatePassword();
		
		System.out.println(oprId);
	
		try {
			OperatorDTO oprDTO = oprDAO.getOperator(Integer.parseInt(oprId));
			
			oprDTO.setOprPassword(newPass);
			
			oprDAO.updateOperator(oprDTO);
			
			mailobj.sendMail(oprDTO.getOprEmail(), 
					"Hej" + oprDTO.getOprFirstName() + " " + oprDTO.getOprLastName() + ", \n"
					+ "Du har fået et nyt password. \n"
					+ "Dine nye login oplysinger er: \n"
					+ "id: " + oprDTO.getOprId() + "\n"
					+ "Password: " + newPass + "\n"
					+ "\n"
					+ "//Din ynglings Webservice" , "New Password");
			return Response.status(200).entity("Mail sent").build();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(400).entity("Mail failed").build();
	}
	
}