package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dto.ReceptDTO;
import final_cdio_11.java.data.validator.IReceptValidator;
import final_cdio_11.java.data.validator.ReceptValidator;
import final_cdio_11.java.handler.TextHandler;

public class ReceptController implements IReceptController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private IReceptValidator receptValidator = new ReceptValidator();

	@Override
	public List<ReceptDTO> getReceptList() {
		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		List<ReceptDTO> receptList = null;

		try {
			receptList = receptDAO.getReceptList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return receptList;
	}

	@Override
	public Response createRecept(ReceptDTO receptDTO) {
		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		if (!receptValidator.isReceptValid(receptDTO)) return Response.status(400).entity(textHandler.errReceiptInvalid).build();

		try {
			receptDAO.createRecept(receptDTO);
			return Response.status(200).entity(textHandler.succReceiptCreate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errReceiptCreate).build();
	}

	@Override
	public Response updateRecept(ReceptDTO receptDTO) {
		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		if (!receptValidator.isReceptValid(receptDTO)) return Response.status(400).entity(textHandler.errReceiptInvalid).build();

		try {
			receptDAO.updateRecept(receptDTO);
			return Response.status(200).entity(textHandler.succReceiptUpdate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errReceiptUpdate).build();
	}

	@Override
	public Response deleteRecept(int receptId) {
		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.deleteRecept(receptId);
			return Response.status(200).entity(textHandler.succReceiptDelete).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errReceiptDelete).build();
	}

}