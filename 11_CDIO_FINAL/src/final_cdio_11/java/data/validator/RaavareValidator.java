package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareDTO;
import final_cdio_11.java.utils.Utils;

public class RaavareValidator implements IRaavareValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isRaavareIdValid(int raavareId) {
		boolean isRaavareIdValid = raavareId >= 1 && raavareId <= 99999999;
		if (!isRaavareIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRaavareIdValid");
		return isRaavareIdValid;
	}

	@Override
	public boolean isRaavareNameValid(String raavareName) {
		if (raavareName == null) return false;
		boolean isRaavareNameValid = raavareName.length() >= 2 && raavareName.length() <= 20;
		if (!isRaavareNameValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRaavareNameValid");
		return isRaavareNameValid;
	}

	@Override
	public boolean isSupplierValid(String supplier) {
		if (supplier == null) return false;
		boolean isSupplierValid = supplier.length() >= 2 && supplier.length() <= 20;
		if (!isSupplierValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isSupplierValid");
		return isSupplierValid;
	}

	@Override
	public boolean isRaavareStatusValid(int status) {
		boolean isRaavareStatusValid = status == 0 || status == 1;
		if (!isRaavareStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRaavareStatusValid");
		return isRaavareStatusValid;
	}

	@Override
	public boolean isRaavareValid(RaavareDTO raavareDTO) {
		return isRaavareIdValid(raavareDTO.getRaavareId()) && isRaavareNameValid(raavareDTO.getRaavareName()) && isSupplierValid(raavareDTO.getSupplier()) && isRaavareStatusValid(raavareDTO.getStatus());
	}

}