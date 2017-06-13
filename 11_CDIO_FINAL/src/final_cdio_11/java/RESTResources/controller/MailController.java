package final_cdio_11.java.RESTResources.controller;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.MailPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLOperatorDAO;
import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.handler.MailHandler;
import final_cdio_11.java.handler.TextHandler;
import final_cdio_11.java.utils.Utils;

/*
 * REST Controller for business logic associated with mail related utilities.
 */
public class MailController implements IMailController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private final Utils utils = Utils.getInstance();

	/* Sends an e-mail to an Operators specified e-mail. */
	public Response sendMail(MailPOJO mailPOJO) {
		MailHandler mailObj = new MailHandler();

		try {
			mailObj.sendMail(mailPOJO.getTo(), mailPOJO.getMsg(), mailPOJO.getSub());
			return Response.status(200).entity(textHandler.succMailSent).build();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return Response.status(400).entity(textHandler.errMailFailed).build();
	}

	/* Gives new password to an operator and sends an e-mail. */
	public Response sendNewPassword(String oprId) {
		MailHandler mailobj = new MailHandler();
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		String newPass = utils.generatePassword();

		try {
			OperatorDTO oprDTO = oprDAO.getOperator(Integer.parseInt(oprId));
			oprDAO.updateOperator(new OperatorDTO(oprDTO.getOprId(), oprDTO.getOprFirstName(), oprDTO.getOprLastName(), oprDTO.getOprIni(), oprDTO.getOprEmail(), oprDTO.getOprCpr(), newPass, oprDTO.getStatus()));

			mailobj.sendMail(oprDTO.getOprEmail(), textHandler.mailMessage(oprDTO, newPass), textHandler.mailNewPassSubject);
			if (utils.DEV_ENABLED) utils.logMessage(textHandler.mailMessage(oprDTO, newPass));

			return Response.status(200).entity(textHandler.succMailSent).build();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (DALException e) {
			e.printStackTrace();
		}
		return Response.status(400).entity(textHandler.errMailFailed).build();
	}

}