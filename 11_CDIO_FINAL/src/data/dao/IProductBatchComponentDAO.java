package data.dao;

import java.util.List;

import data.dto.ProductBatchComponentDTO;

/*
 * Interface for the ProductBatchComponentDAO (data access object).
 * The DAO uses a CRUD-style design.
 */
public interface IProductBatchComponentDAO {
	ProductBatchComponentDTO getProductBatchComponent(int pbId, int rbId) throws DALException;
	List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException;
	List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException;
	void createProductBatchComponent(ProductBatchComponentDTO pbc) throws DALException;
	void updateProductBatchComponent(ProductBatchComponentDTO pbc) throws DALException;
	void deleteProductBatchComponent(int pbId, int rbId) throws DALException;
}