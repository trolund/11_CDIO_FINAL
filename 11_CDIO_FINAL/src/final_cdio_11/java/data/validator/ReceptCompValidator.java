package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ReceptComponentDTO;
import final_cdio_11.java.utils.Utils;

public class ReceptCompValidator implements IReceptCompValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isReceptIdValid(int receptId) {
		boolean isReceptIdValid = receptId >= 1 && receptId <= 99999999;
		if (!isReceptIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isReceptIdValid");
		return isReceptIdValid;
	}

	@Override
	public boolean isRaavareIdValid(int raavareId) {
		boolean isRaavareIdValid = raavareId >= 1 && raavareId <= 99999999;
		if (!isRaavareIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRaavareIdValid");
		return isRaavareIdValid;
	}

	@Override
	public boolean isNomNettoValid(double nomNetto) {
		boolean isNomNettoValid = nomNetto >= 0.05 && nomNetto <= 20.0;
		if (!isNomNettoValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isNomNettoValid");
		return isNomNettoValid;
	}

	@Override
	public boolean isToleranceValid(double tolerance) {
		boolean isToleranceValid = tolerance >= 0.1 && tolerance <= 10.0;
		if (!isToleranceValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isToleranceValid");
		return isToleranceValid;
	}

	@Override
	public boolean isRcStatusValid(int status) {
		boolean isRcStatusValid = status == 0 || status == 1;
		if (!isRcStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isRcStatusValid");
		return isRcStatusValid;
	}

	@Override
	public boolean isRcValid(ReceptComponentDTO rcDTO) {
		return isReceptIdValid(rcDTO.getReceptId()) && isRaavareIdValid(rcDTO.getRaavareId()) && isNomNettoValid(rcDTO.getNomNetto()) && isToleranceValid(rcDTO.getTolerance()) && isRcStatusValid(rcDTO.getStatus());
	}

}