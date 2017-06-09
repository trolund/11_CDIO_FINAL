package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLRaavareDAO;
import final_cdio_11.java.data.dto.RaavareDTO;

public class RaavareController implements IRaavareController {

	@Override
	public List<RaavareDTO> List() {
		SQLRaavareDAO DAO = new SQLRaavareDAO(Connector.getInstance());

		List<RaavareDTO> list = null;

		try {
			list = DAO.getRaavareList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Response Update(RaavareDTO data) {

		SQLRaavareDAO DAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			DAO.updateRaavare(data);
			System.out.println("update done!");
			return Response.status(200).entity("Recept updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response del(int id) {
		SQLRaavareDAO RaavareDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			RaavareDAO.deleteRaavare(id);
			return Response.status(200).entity("Recept deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	public Response insert(RaavareDTO data) {
		SQLRaavareDAO RaavareDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			RaavareDAO.createRaavare(data);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

}