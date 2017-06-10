package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IReceptDAO;
import final_cdio_11.java.data.dao.SQLReceptDAO;
import final_cdio_11.java.data.dto.ReceptDTO;

public class ReceptController implements IReceptController {

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
	public Response updateRecept(ReceptDTO receptDTO) {

		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.updateRecept(receptDTO);
			System.out.println("update done!");
			return Response.status(200).entity("Recept updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response delRecept(int receptId) {
		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.deleteRecept(receptId);
			return Response.status(200).entity("Recept deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response createRecept(ReceptDTO receptDTO) {
		IReceptDAO receptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			receptDAO.createRecept(receptDTO);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

}