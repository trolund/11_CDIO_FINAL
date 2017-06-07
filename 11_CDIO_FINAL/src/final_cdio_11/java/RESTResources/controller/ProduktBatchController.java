package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.PbPOJO;
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
	
	@Override
	public Response UpdatePB(PbPOJO data) {
		
		ProductBatchDTO dto = new ProductBatchDTO(Integer.parseInt(data.getPbId()),Integer.parseInt(data.getItemStatus()), Integer.parseInt(data.getReceptId()), Integer.parseInt(data.getStatus()));
	
		SQLProductBatchDAO view = new SQLProductBatchDAO(Connector.getInstance());

		try {
			view.updateProductBatch(dto);
			return Response.status(200).entity("pb updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}
	
	
	@Override
	public Response delPB(int id) {
		SQLProductBatchDAO view = new SQLProductBatchDAO(Connector.getInstance());

		try {
			view.deleteProductBatch(id);;
			return Response.status(200).entity("pb deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

}