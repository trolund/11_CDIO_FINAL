package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ProductBatchComponentDTO;

/*
 * Interface for the productbatchcomponent validator class.
 * This interface contains various validation methods
 * for the ProductBatchComponentDTO.
 */
public interface IProductBatchCompValidator {
	boolean isPbIdValid(int pbId);
	boolean isRbIdValid(int rbId);
	boolean isTareValid(double tare);
	boolean isNettoValid(double netto);
	boolean isOprIdValid(int oprId);
	boolean isPbcStatusValid(int status);
	boolean isPBCValid(ProductBatchComponentDTO pbcDTO);
}