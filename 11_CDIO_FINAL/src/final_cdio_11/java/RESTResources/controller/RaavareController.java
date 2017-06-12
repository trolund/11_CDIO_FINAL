package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IRaavareDAO;
import final_cdio_11.java.data.dao.SQLRaavareDAO;
import final_cdio_11.java.data.dto.RaavareDTO;
import final_cdio_11.java.data.validator.RaavareValidator;
import final_cdio_11.java.handler.TextHandler;

public class RaavareController implements IRaavareController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private RaavareValidator raavareValidator = new RaavareValidator();

	@Override
	public List<RaavareDTO> getRaavareList() {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		List<RaavareDTO> raavareList = null;

		try {
			raavareList = raavareDAO.getRaavareList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return raavareList;
	}

	@Override
	public Response createRaavare(RaavareDTO raavareDTO) {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		if (!raavareValidator.isRaavareValid(raavareDTO)) return Response.status(400).entity(textHandler.errRaavareInvalid).build();

		try {
			raavareDAO.createRaavare(raavareDTO);
			return Response.status(200).entity(textHandler.succRaavareCreate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRaavareCreate).build();
	}

	@Override
	public Response updateRaavare(RaavareDTO raavareDTO) {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		if (!raavareValidator.isRaavareValid(raavareDTO)) return Response.status(400).entity(textHandler.errRaavareInvalid).build();

		try {
			raavareDAO.updateRaavare(raavareDTO);
			return Response.status(200).entity(textHandler.succRaavareUpdate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRaavareUpdate).build();
	}

	@Override
	public Response deleteRaavare(int raavareId) {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			raavareDAO.deleteRaavare(raavareId);
			return Response.status(200).entity(textHandler.succRaavareDelete).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRaavareDelete).build();
	}

}