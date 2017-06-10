package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import final_cdio_11.java.data.dto.view.VUserTableDTO;

public interface IViewController {
	List<VUserTableDTO> getVUserTableList();
}