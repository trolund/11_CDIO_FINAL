package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.ReceptDTO;

/*
 * Interface for the REST ReceptController.
 * This interface contains methods related to Receipt.
 */
public interface IReceptController {
	List<ReceptDTO> getReceptList();
	Response createRecept(ReceptDTO receptDTO);
	Response updateRecept(ReceptDTO receptDTO);
	Response deleteRecept(int receptId);
}