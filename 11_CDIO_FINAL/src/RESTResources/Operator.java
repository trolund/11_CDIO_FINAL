package RESTResources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Connector;
import data.dao.DALException;
import data.dao.SQLOperatorDAO;
import data.dto.OperatorDTO;
import utils.SecUtils;

@Path("/opr")
public class Operator {

	/*
	 * I stedet for at have en oprDAO i hver metode, hvorfor saa ikke
	 * have en final global oprDAO?
	 */

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser() {
		return "If you see this, REST should be working correctly!";
	}

	@POST
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public String verify(@FormParam("oprId") String oprId, @FormParam("password") String password) {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		SecUtils secUtil = SecUtils.getInstance();

		OperatorDTO oprDTO = null;
		try {
			oprDTO = oprDAO.getOperator(Integer.parseInt(oprId));
		} catch (DALException e) {
			e.printStackTrace();
			return "ID does not exist.";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Invalid ID.";
		}

		if (secUtil.sha256(password).equals(oprDTO.getOprPassword())) {
			return "Correct password.";
		} else {
			return "Invalid credentials.";
		}
	}

	@GET
	@Path("/getName")
	@Produces(MediaType.TEXT_PLAIN)
	public String getName() {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());
		OperatorDTO oprDTO = null;

		int oprId = 1;
		try {
			oprDTO = oprDAO.getOperator(oprId);
		} catch (DALException e) {
			e.printStackTrace();
			return "OperatorDTO with ID [" + oprId + "] does not exist!";
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "Invalid ID.";
		}
		return oprDTO.getOprName();
	}

	@GET
	@Path("/getOprList")
	@Produces(MediaType.TEXT_HTML)
	public String getopr() {
		SQLOperatorDAO oprDAO = new SQLOperatorDAO(Connector.getInstance());

		StringBuilder returnString = new StringBuilder();

		List<OperatorDTO> oprList = null;
		try {
			oprList = oprDAO.getOperatorList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		for (OperatorDTO oprDTO : oprList) {
			returnString.append("<table>");
			returnString.append("<tr>" + "<td>" + oprDTO.getOprId() + "</td>" + "<td>" + oprDTO.getOprIni() + "</td>" + "<td>" + oprDTO.getOprName() + "</td>" + "<td>" + oprDTO.getOprCpr() + "</td>" + "<td>" + oprDTO.getOprPassword() + "</td>" + "</tr>");
			returnString.append("</table>");
		}
		return returnString.toString();
	}

}