package final_cdio_11.java.RESTResources.controller;

import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.MailPOJO;

public interface IMailController {
	Response sendMail(MailPOJO mailPOJO);
	Response sendNewPassword(String oprId);
}