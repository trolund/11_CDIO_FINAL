package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dto.ProductBatchDTO;

public class ProduktBatchController implements IProduktBatchController {

	@Override
	public List<ProductBatchDTO> ProduktBatchList() {
		SQLProductBatchDAO view = new SQLProductBatchDAO(Connector.getInstance());

		List<ProductBatchDTO> List = null;

		try {
			List = view.getProductBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return List;
	}

}