package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.RaavareBatchDTO;

public interface IRaavareBatchController {

	Response updateRB(RaavareBatchDTO data);

	List<RaavareBatchDTO> getRbList();

	Response delRB(int id);

	Response createRB(RaavareBatchDTO data);

}