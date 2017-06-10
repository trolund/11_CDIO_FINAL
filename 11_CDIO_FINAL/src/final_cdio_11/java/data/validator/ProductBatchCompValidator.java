package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ProductBatchComponentDTO;
import final_cdio_11.java.utils.Utils;

public class ProductBatchCompValidator implements IProductBatchCompValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isPbIdValid(int pbId) {
		boolean isPbIdValid = pbId >= 1 && pbId <= 99999999;
		if (!isPbIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isPbIdValid");
		return isPbIdValid;
	}

	@Override
	public boolean isRbIdValid(int rbId) {
		boolean isRbIdValid = rbId >= 1 && rbId <= 99999999;
		if (!isRbIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRbIdValid");
		return isRbIdValid;
	}

	@Override
	public boolean isTareValid(double tare) {
		boolean isTareValid = !Double.isNaN(tare);
		if (!isTareValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isTareValid");
		return isTareValid;
	}

	@Override
	public boolean isNettoValid(double netto) {
		boolean isNettoValid = !Double.isNaN(netto);
		if (!isNettoValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isNettoValid");
		return isNettoValid;
	}

	@Override
	public boolean isOprIdValid(int oprId) {
		boolean isOprIdValid = oprId >= 1 && oprId <= 99999999;
		if (!isOprIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprIdValid");
		return isOprIdValid;
	}

	@Override
	public boolean isPbcStatusValid(int status) {
		boolean isPbcStatusValid = status == 0 | status == 1;
		if (!isPbcStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isPbcStatusValid");
		return isPbcStatusValid;
	}

	@Override
	public boolean isPBCValid(ProductBatchComponentDTO pbcDTO) {
		return isPbIdValid(pbcDTO.getpbId()) && isRbIdValid(pbcDTO.getrbId()) && isTareValid(pbcDTO.getTara()) && isNettoValid(pbcDTO.getNetto()) && isOprIdValid(pbcDTO.getOprId()) && isPbcStatusValid(pbcDTO.getStatus());
	}

}