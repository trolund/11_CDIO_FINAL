package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.view.IVUserTableDAO;
import final_cdio_11.java.data.dao.view.SQLVUserTableDAO;
import final_cdio_11.java.data.dto.view.VUserTableDTO;

/*
 * REST Controller for business logic associated with VUserTable related utilities.
 */
public class ViewController implements IViewController {

	/* Returns UserTableView list. */
	@Override
	public List<VUserTableDTO> getVUserTableList() {
		IVUserTableDAO view = new SQLVUserTableDAO(Connector.getInstance());

		List<VUserTableDTO> viewList = null;

		try {
			viewList = view.getVUserTableList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return viewList;
	}

}