package final_cdio_11.java.RESTResources.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import final_cdio_11.java.RESTResources.pojo.PbPOJO;
import final_cdio_11.java.data.dto.ProductBatchDTO;

public interface IProductBatchController {
	List<ProductBatchDTO> getProductBatchList();
	List<String> getProductBatchReceptIdList();
	Response createProductBatch(PbPOJO pbPOJO);
	Response updateProductBatch(PbPOJO pbPOJO);
	Response deleteProductBatch(int pbId);
}