package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.RaavareDTO;

public interface IRaavareController {

	List<RaavareDTO> getRaavareList();

	Response updateRaavare(RaavareDTO data);

	Response delRaavare(int id);

	Response createRaavare(RaavareDTO data);

}