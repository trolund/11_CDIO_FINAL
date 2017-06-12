package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareBatchDTO;
import final_cdio_11.java.utils.Utils;

public class RaavareBatchValidator implements IRaavareBatchValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isRbIdValid(int rbId) {
		boolean isRbIdValid = rbId >= 1 && rbId <= 99999999;
		if (!isRbIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRbIdValid");
		return isRbIdValid;
	}

	@Override
	public boolean isRaavareIdValid(int raavareId) {
		boolean isRaavareIdValid = raavareId >= 1 && raavareId <= 99999999;
		if (!isRaavareIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRaavareIdValid");
		return isRaavareIdValid;
	}

	@Override
	public boolean isAmountValid(double amount) {
		boolean isAmountValid = amount >= 0 && amount <= 99999999;
		if (!isAmountValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isAmountValid");
		return isAmountValid;
	}

	@Override
	public boolean isRbStatusValid(int status) {
		boolean isRbStatusValid = status == 0 || status == 1;
		if (!isRbStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRbStatusValid");
		return isRbStatusValid;
	}

	@Override
	public boolean isRbValid(RaavareBatchDTO rbDTO) {
		return isRbIdValid(rbDTO.getRbId()) && isRaavareIdValid(rbDTO.getRaavareId()) && isAmountValid(rbDTO.getAmount()) && isRbStatusValid(rbDTO.getStatus());
	}

}