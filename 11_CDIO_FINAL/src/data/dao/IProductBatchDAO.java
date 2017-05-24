package data.dao;

import java.util.List;

import data.dto.ProductBatchDTO;

/*
 * Interface for the ProductBatchDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IProductBatchDAO {
	ProductBatchDTO getProductBatch(int pbId) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatchDTO pbDTO) throws DALException;
	void updateProductBatch(ProductBatchDTO pbDTO) throws DALException;
	void deleteProductBatch(int pbId) throws DALException;
}