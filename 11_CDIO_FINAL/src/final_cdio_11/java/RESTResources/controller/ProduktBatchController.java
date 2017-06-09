package final_cdio_11.java.RESTResources.controller;

import java.util.ArrayList;
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
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		List<ProductBatchDTO> list = null;

		try {
			list = pbDAO.getProductBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Response UpdatePB(PbPOJO data) {

		ProductBatchDTO pbDTO = new ProductBatchDTO(Integer.parseInt(data.getPbId()), Integer.parseInt(data.getItemStatus()), Integer.parseInt(data.getReceptId()), Integer.parseInt(data.getStatus()));

		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		try {
			pbDAO.updateProductBatch(pbDTO);
			System.out.println("update done!");
			return Response.status(200).entity("pb updated").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public Response delPB(int id) {
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		try {
			pbDAO.deleteProductBatch(id);
			;
			return Response.status(200).entity("pb deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	public Response insertPB(PbPOJO pbPOJO) {
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		ProductBatchDTO dto = new ProductBatchDTO(Integer.parseInt(pbPOJO.getPbId()), Integer.parseInt(pbPOJO.getItemStatus()), Integer.parseInt(pbPOJO.getReceptId()), Integer.parseInt(pbPOJO.getStatus()));

		try {
			pbDAO.createProductBatch(dto);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public List<String> ProduktBatchReceptidList() {
		SQLProductBatchDAO view = new SQLProductBatchDAO(Connector.getInstance());

		List<ProductBatchDTO> List = null;
		List<String> ListString = new ArrayList<String>();
		;

		try {
			List = view.getProductBatchList();

			for (int i = 0; i < List.size(); i++) {
				String x = "" + List.get(i).getReceptId();
				ListString.add(x);
			}

		} catch (DALException e) {
			e.printStackTrace();
		}
		return ListString;
	}
}