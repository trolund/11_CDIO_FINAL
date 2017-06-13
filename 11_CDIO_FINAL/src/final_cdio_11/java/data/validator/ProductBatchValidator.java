package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ProductBatchDTO;
import final_cdio_11.java.utils.Utils;

/*
 * Data transfer object validator class.
 * This class implements various validation methods
 * to ensure data integrity.
 */
public class ProductBatchValidator implements IProductBatchValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isPbIdValid(int pbId) {
		boolean isPbIdValid = pbId >= 1 && pbId <= 99999999;
		if (!isPbIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isPbIdValid");
		return isPbIdValid;
	}

	@Override
	public boolean isItemStatusValid(int itemStatus) {
		boolean isItemStatusValid = itemStatus == 0 || itemStatus == 1 || itemStatus == 2;
		if (!isItemStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isItemStatusValid");
		return isItemStatusValid;
	}

	@Override
	public boolean isReceptIdValid(int receptId) {
		boolean isReceptIdValid = receptId >= 1 && receptId <= 99999999;
		if (!isReceptIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isReceptIdValid");
		return isReceptIdValid;
	}

	@Override
	public boolean isPbStatusValid(int status) {
		boolean isPbStatusValid = status == 0 || status == 1;
		if (!isPbStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isPbStatusValid");
		return isPbStatusValid;
	}

	@Override
	public boolean isPbValid(ProductBatchDTO pbDTO) {
		return isPbIdValid(pbDTO.getPbId()) && isItemStatusValid(pbDTO.getItemStatus()) && isReceptIdValid(pbDTO.getReceptId()) && isPbStatusValid(pbDTO.getStatus());
	}

}