package final_cdio_11.java.RESTResources.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.PbPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.data.validator.IProductBatchValidator;
import final_cdio_11.java.data.validator.ProductBatchValidator;

public class ProduktBatchController implements IProduktBatchController {

	private final IProductBatchValidator pbValidator = new ProductBatchValidator();

	@Override
	public List<ProductBatchDTO> getProduktBatchList() {
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
	public Response updatePB(PbPOJO data) {

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
			return Response.status(200).entity("pb deleted").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	public Response createPB(PbPOJO pbPOJO) {
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		ProductBatchDTO pbDTO = new ProductBatchDTO(Integer.parseInt(pbPOJO.getPbId()), Integer.parseInt(pbPOJO.getItemStatus()), Integer.parseInt(pbPOJO.getReceptId()), Integer.parseInt(pbPOJO.getStatus()));

		if (!pbValidator.isPbValid(pbDTO)) return Response.status(400).entity("pbDTO has invalid fields.").build();

		try {
			pbDAO.createProductBatch(pbDTO);
			return Response.status(200).entity("pb created").build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity("fejl").build();
	}

	@Override
	public List<String> getProduktBatchReceptIdList() {
		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		List<ProductBatchDTO> list = null;
		List<String> listString = new ArrayList<String>();

		try {
			list = pbDAO.getProductBatchList();

			for (int i = 0; i < list.size(); i++) {
				String str = "" + list.get(i).getReceptId();
				listString.add(str);
			}

		} catch (DALException e) {
			e.printStackTrace();
		}
		return listString;
	}
}