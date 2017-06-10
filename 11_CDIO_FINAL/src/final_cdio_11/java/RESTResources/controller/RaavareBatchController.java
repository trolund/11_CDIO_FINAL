package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLRaavareBatchDAO;
import final_cdio_11.java.data.dto.RaavareBatchDTO;

public class RaavareBatchController implements IRaavareBatchController {

	@Override
	public List<RaavareBatchDTO> getRbList() {
		SQLRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		List<RaavareBatchDTO> rbList = null;

		try {
			rbList = rbDAO.getRaavareBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return rbList;
	}

	@Override
	public Response updateRB(RaavareBatchDTO rbDTO) {
		SQLRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			rbDAO.updateRaavareBatch(rbDTO);
			System.out.println("update done!");
			return Response.status(200).entity("rb updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response delRB(int rbId) {
		SQLRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			rbDAO.deleteRaavareBatch(rbId);
			return Response.status(200).entity("RB deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response createRB(RaavareBatchDTO rbDTO) {
		SQLRaavareBatchDAO rbDAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			rbDAO.createRaavareBatch(rbDTO);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

}