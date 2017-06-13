package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import final_cdio_11.java.data.dto.view.VUserTableDTO;

/*
 * Interface for the REST ViewController.
 * This interface contains methods related to views.
 */
public interface IViewController {
	List<VUserTableDTO> getVUserTableList();
}