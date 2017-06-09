package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;

import final_cdio_11.java.data.dao.SQLReceptDAO;

import final_cdio_11.java.data.dto.ReceptDTO;

public class ReceptController implements IReceptController {
	
	@Override
	public List<ReceptDTO> List() {
		SQLReceptDAO pbDAO = new SQLReceptDAO(Connector.getInstance());

		List<ReceptDTO> list = null;

		try {
			list = pbDAO.getReceptList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Response Update(ReceptDTO data) {

		SQLReceptDAO ReceptDTODAO = new SQLReceptDAO(Connector.getInstance());

		try {
			ReceptDTODAO.updateRecept(data);;
			System.out.println("update done!");
			return Response.status(200).entity("Recept updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response del(int id) {
		SQLReceptDAO ReceptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			ReceptDAO.deleteRecept(id);;
			;
			return Response.status(200).entity("Recept deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	public Response insert(ReceptDTO data) {
		SQLReceptDAO ReceptDAO = new SQLReceptDAO(Connector.getInstance());

		try {
			ReceptDAO.createRecept(data);;
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}
	

}