package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.RaavareBatchDTO;

public interface IRaavareBatchController {

	Response Update(RaavareBatchDTO data);

	List<RaavareBatchDTO> List();

	Response del(int id);

	Response insert(RaavareBatchDTO data);

}