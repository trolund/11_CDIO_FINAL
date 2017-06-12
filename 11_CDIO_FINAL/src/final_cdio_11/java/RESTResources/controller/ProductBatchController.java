package final_cdio_11.java.RESTResources.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.PbPOJO;
import final_cdio_11.java.data.Connector;
import final_cdio_11.java.data.DALException;
import final_cdio_11.java.data.dao.IProductBatchDAO;
import final_cdio_11.java.data.dao.SQLProductBatchDAO;
import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.data.validator.IProductBatchValidator;
import final_cdio_11.java.data.validator.ProductBatchValidator;
import final_cdio_11.java.handler.TextHandler;

public class ProductBatchController implements IProductBatchController {

	private final TextHandler textHandler = TextHandler.getInstance();
	private final IProductBatchValidator pbValidator = new ProductBatchValidator();

	@Override
	public List<ProductBatchDTO> getProductBatchList() {
		IProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		List<ProductBatchDTO> pbList = null;

		try {
			pbList = pbDAO.getProductBatchList();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return pbList;
	}

	@Override
	public List<String> getProductBatchReceptIdList() {
		IProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

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

	public Response createProductBatch(PbPOJO pbPOJO) {
		IProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		ProductBatchDTO pbDTO = new ProductBatchDTO(Integer.parseInt(pbPOJO.getPbId()), Integer.parseInt(pbPOJO.getItemStatus()), Integer.parseInt(pbPOJO.getReceptId()), Integer.parseInt(pbPOJO.getStatus()));

		if (!pbValidator.isPbValid(pbDTO)) return Response.status(400).entity(textHandler.errPbInvalid).build();

		try {
			pbDAO.createProductBatch(pbDTO);
			return Response.status(200).entity(textHandler.succPbCreate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errPbCreate).build();
	}

	@Override
	public Response updateProductBatch(PbPOJO data) {
		ProductBatchDTO pbDTO = new ProductBatchDTO(Integer.parseInt(data.getPbId()), Integer.parseInt(data.getItemStatus()), Integer.parseInt(data.getReceptId()), Integer.parseInt(data.getStatus()));

		if (!pbValidator.isPbValid(pbDTO)) return Response.status(400).entity(textHandler.errPbInvalid).build();

		SQLProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		try {
			pbDAO.updateProductBatch(pbDTO);
			return Response.status(200).entity(textHandler.succPbUpdate).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errPbUpdate).build();
	}

	@Override
	public Response deleteProductBatch(int pbId) {
		IProductBatchDAO pbDAO = new SQLProductBatchDAO(Connector.getInstance());

		try {
			pbDAO.deleteProductBatch(pbId);
			return Response.status(200).entity(textHandler.succPbDelete).build();
		} catch (DALException e) {
			e.printStackTrace();
		}

		return Response.status(400).entity(textHandler.errPbDelete).build();
	}

}