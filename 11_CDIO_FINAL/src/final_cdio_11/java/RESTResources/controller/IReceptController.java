package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.ReceptDTO;

public interface IReceptController {

	List<ReceptDTO> List();

	Response Update(ReceptDTO data);

	Response del(int id);

	Response insert(ReceptDTO data);

}