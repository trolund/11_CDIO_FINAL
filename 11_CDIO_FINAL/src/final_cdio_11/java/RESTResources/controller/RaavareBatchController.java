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

public class RaavareBatchController implements IRaavareBatchController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private IRaavareBatchValidator rbValidator = new RaavareBatchValidator();

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

	@Override
	public Response createRaavareBatch(RaavareBatchDTO rbDTO) {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		if (!rbValidator.isRbValid(rbDTO)) return Response.status(400).entity(textHandler.errRbInvalid).build();

		try {
			rbDAO.createRaavareBatch(rbDTO);
			return Response.status(200).entity(textHandler.succRbCreate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRbCreate).build();
	}

	@Override
	public Response updateRaavareBatch(RaavareBatchDTO rbDTO) {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		if (!rbValidator.isRbValid(rbDTO)) return Response.status(400).entity(textHandler.errRbInvalid).build();

		try {
			rbDAO.updateRaavareBatch(rbDTO);
			return Response.status(200).entity(textHandler.succRbUpdate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRbUpdate).build();
	}

	@Override
	public Response deleteRaavareBatch(int rbId) {
		IRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			rbDAO.deleteRaavareBatch(rbId);
			return Response.status(200).entity(textHandler.succRbDelete).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRbDelete).build();
	}

}