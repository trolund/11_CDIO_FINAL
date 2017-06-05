package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.IVPharmacistRecipeDAO;
import final_cdio_11.java.data.dao.view.SQLPharmacistRecipeDAO;
import final_cdio_11.java.data.dao.view.SQLVUserTableDAO;
import final_cdio_11.java.data.dto.view.VPharmacistRecipeDTO;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

public class ViewController implements IViewController {

	@Override
	public List<VUserTableDTO> VUserTableList() {
		SQLVUserTableDAO view = new SQLVUserTableDAO(Connector.getInstance());

			List<VUserTableDTO> viewList = null;

			try {
				viewList = view.getVUserTableList();
			} catch (DALException e) {
				e.printStackTrace();
			}
			return viewList;
		}
}