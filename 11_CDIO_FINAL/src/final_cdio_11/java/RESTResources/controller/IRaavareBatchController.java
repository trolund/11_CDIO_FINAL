package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.RaavareBatchDTO;

/*
 * Interface for the REST RaavareBatchController.
 * This interface contains methods related to RaavareBatch.
 */
public interface IRaavareBatchController {
	List<RaavareBatchDTO> getRaavareBatchList();
	Response createRaavareBatch(RaavareBatchDTO rbDTO);
	Response updateRaavareBatch(RaavareBatchDTO rbDTO);
	Response deleteRaavareBatch(int rbId);
}