package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ProductBatchDTO;

/*
 * Interface for the productbatch validator class.
 * This interface contains various validation methods
 * for the ProductBatchDTO.
 */
public interface IProductBatchValidator {
	boolean isPbIdValid(int pbId);
	boolean isItemStatusValid(int itemStatus);
	boolean isReceptIdValid(int receptId);
	boolean isPbStatusValid(int status);
	boolean isPbValid(ProductBatchDTO pbDTO);
}