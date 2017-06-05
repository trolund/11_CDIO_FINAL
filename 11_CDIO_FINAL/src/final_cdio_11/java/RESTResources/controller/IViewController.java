package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.MailPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.IVPharmacistRecipeDAO;
import final_cdio_11.java.data.dao.view.SQLPharmacistRecipeDAO;
import final_cdio_11.java.data.dto.view.VPharmacistRecipeDTO;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

public interface IViewController {
	
	List<VUserTableDTO> VUserTableList();

}