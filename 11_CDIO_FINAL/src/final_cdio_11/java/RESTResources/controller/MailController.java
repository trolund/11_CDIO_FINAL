package final_cdio_11.java.RESTResources.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.MailPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.utils.MailHandler;
import final_cdio_11.java.utils.TextHandler;
import final_cdio_11.java.utils.Utils;

public class MailController implements IMailController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private final Utils utils = Utils.getInstance();

	public Response sendMail(MailPOJO mailPOJO) {
		MailHandler mailObj = new MailHandler();

		try {
			mailObj.sendMail(mailPOJO.getTo(), mailPOJO.getMsg(), mailPOJO.getSub());
			return Response.status(200).entity("Mail sent").build();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return Response.status(400).entity("Mail failed").build();
	}

	public Response sendNewPassword(String oprId) {
		MailHandler mailobj = new MailHandler();
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