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

/*
 * REST Controller for business logic associated with raavare related utilities.
 */
public class RaavareController implements IRaavareController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private RaavareValidator raavareValidator = new RaavareValidator();

	/* Get Raavare List from the underlying data layer. */
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

	/* create Raavare in the underlying data layer. */
	@Override
	public Response createRaavare(RaavareDTO raavareDTO) {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		if (!raavareValidator.isRaavareValid(raavareDTO)) return Response.status(400).entity(textHandler.errRaavareInvalid).type("text/plain").build();

		try {
			raavareDAO.createRaavare(raavareDTO);
			return Response.status(200).entity(textHandler.succRaavareCreate).type("text/plain").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRaavareCreate).type("text/plain").build();
	}

	/* update Raavare in the underlying data layer. */
	@Override
	public Response updateRaavare(RaavareDTO raavareDTO) {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		if (!raavareValidator.isRaavareValid(raavareDTO)) return Response.status(400).entity(textHandler.errRaavareInvalid).type("text/plain").build();

		try {
			raavareDAO.updateRaavare(raavareDTO);
			return Response.status(200).entity(textHandler.succRaavareUpdate).type("text/plain").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRaavareUpdate).type("text/plain").build();
	}

	/* delete Raavare in the underlying data layer. */
	@Override
	public Response deleteRaavare(int raavareId) {
		IRaavareDAO raavareDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			raavareDAO.deleteRaavare(raavareId);
			return Response.status(200).entity(textHandler.succRaavareDelete).type("text/plain").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errRaavareDelete).type("text/plain").build();
	}

}