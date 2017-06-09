package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.RaavareDTO;
import final_cdio_11.java.data.dto.ReceptDTO;

public interface IRaavareController {

	List<RaavareDTO> List();

	Response Update(RaavareDTO data);

	Response del(int id);

	Response insert(RaavareDTO data);

}