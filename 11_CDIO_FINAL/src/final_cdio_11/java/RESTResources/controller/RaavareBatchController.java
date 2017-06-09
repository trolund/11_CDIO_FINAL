package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLRaavareBatchDAO;
import final_cdio_11.java.data.dto.RaavareBatchDTO;

public class RaavareBatchController implements IRaavareBatchController {
	
	@Override
	public List<RaavareBatchDTO> List() {
		SQLRaavareBatchDAO DAO = new SQLRaavareBatchDAO(Connector.getInstance());

		List<RaavareBatchDTO> list = null;

		try {
			list = DAO.getRaavareBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Response Update(RaavareBatchDTO data) {

		SQLRaavareBatchDAO DAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			DAO.updateRaavareBatch(data);
			System.out.println("update done!");
			return Response.status(200).entity("Recept updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response del(int id) {
		SQLRaavareBatchDAO DAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			DAO.deleteRaavareBatch(id);
			;
			return Response.status(200).entity("Recept deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	public Response insert(RaavareBatchDTO data) {
		SQLRaavareBatchDAO DAO = new SQLRaavareBatchDAO(Connector.getInstance());

		try {
			DAO.createRaavareBatch(data);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}
	

}