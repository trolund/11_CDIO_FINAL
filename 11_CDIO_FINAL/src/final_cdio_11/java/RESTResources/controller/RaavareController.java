package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLRaavareDAO;
import final_cdio_11.java.data.dto.RaavareDTO;

public class RaavareController implements IRaavareController {

	@Override
	public List<RaavareDTO> getRaavareList() {
		SQLRaavareDAO rDAO = new SQLRaavareDAO(Connector.getInstance());

		List<RaavareDTO> rList = null;

		try {
			rList = rDAO.getRaavareList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return rList;
	}

	@Override
	public Response updateRaavare(RaavareDTO rDTO) {

		SQLRaavareDAO rDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			rDAO.updateRaavare(rDTO);
			System.out.println("update done!");
			return Response.status(200).entity("Raavare updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response delRaavare(int rId) {
		SQLRaavareDAO rDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			rDAO.deleteRaavare(rId);
			return Response.status(200).entity("Raavare deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response createRaavare(RaavareDTO rDTO) {
		SQLRaavareDAO rDAO = new SQLRaavareDAO(Connector.getInstance());

		try {
			rDAO.createRaavare(rDTO);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

}