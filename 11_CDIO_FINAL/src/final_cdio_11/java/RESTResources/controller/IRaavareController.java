package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.data.dto.RaavareDTO;

public interface IRaavareController {
	List<RaavareDTO> getRaavareList();
	Response createRaavare(RaavareDTO raavareDTO);
	Response updateRaavare(RaavareDTO raavareDTO);
	Response deleteRaavare(int raavareId);
}