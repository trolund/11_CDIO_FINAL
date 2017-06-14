package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IRaavareBatchDAO;
import final_cdio_11.java.data.dao.SQLRaavareBatchDAO;
import final_cdio_11.java.data.dto.RaavareBatchDTO;
import final_cdio_11.java.data.validator.IRaavareBatchValidator;
import final_cdio_11.java.data.validator.RaavareBatchValidator;
import final_cdio_11.java.handler.TextHandler;

/*
 * REST Controller for business logic associated with RaavareBatch related utilities.
 */
public class RaavareBatchController implements IRaavareBatchController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private IRaavareBatchValidator rbValidator = new RaavareBatchValidator();

	/* Get RaavareBatch list from the underlying data layer. */
	@Override
	public List<RaavareBatchDTO> getRaavareBatchList() {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		List<RaavareBatchDTO> rbList = null;

		try {
			rbList = rbDAO.getRaavareBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return rbList;
	}

	/* Create RaavareBatch in the underlying data layer. */
	@Override
	public Response createRaavareBatch(RaavareBatchDTO rbDTO) {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		if (!rbValidator.isRbValid(rbDTO)) return Response.status(400).entity(textHandler.errRbInvalid).type("text/plain").build();

		try {
			rbDAO.createRaavareBatch(rbDTO);
			return Response.status(200).entity(textHandler.succRbCreate).type("text/plain").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRbCreate).type("text/plain").build();
	}

	/* Update RaavareBatch in the underlying data layer. */
	@Override
	public Response updateRaavareBatch(RaavareBatchDTO rbDTO) {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		if (!rbValidator.isRbValid(rbDTO)) return Response.status(400).entity(textHandler.errRbInvalid).type("text/plain").build();

		try {
			rbDAO.updateRaavareBatch(rbDTO);
			return Response.status(200).entity(textHandler.succRbUpdate).type("text/plain").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRbUpdate).type("text/plain").build();
	}

	/* Delete pdate RaavareBatch in the underlying data layer. */
	@Override
	public Response deleteRaavareBatch(int rbId) {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			rbDAO.deleteRaavareBatch(rbId);
			return Response.status(200).entity(textHandler.succRbDelete).type("text/plain").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRbDelete).type("text/plain").build();
	}

}