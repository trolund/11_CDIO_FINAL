package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ProductBatchDTO;

public class ProductBatchValidator implements IProductBatchValidator {

	@Override
	public boolean isPbIdValid(int pbId) {
		return pbId >= 1 && pbId <= 99999999;
	}

	@Override
	public boolean isItemStatusValid(int itemStatus) {
		return itemStatus == 0 || itemStatus == 1 || itemStatus == 2;
	}

	@Override
	public boolean isReceptIdValid(int receptId) {
		return receptId >= 1 && receptId <= 99999999;
	}

	@Override
	public boolean isPbStatusValid(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isPbValid(ProductBatchDTO pbDTO) {
		return isPbIdValid(pbDTO.getPbId()) && isItemStatusValid(pbDTO.getItemStatus()) && isReceptIdValid(pbDTO.getReceptId()) && isPbStatusValid(pbDTO.getStatus());
	}

}