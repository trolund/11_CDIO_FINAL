package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ReceptDTO;
import final_cdio_11.java.utils.Utils;

public class ReceptValidator implements IReceptValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isReceptIdValid(int receptId) {
		boolean isReceptIdValid = receptId >= 1 && receptId <= 99999999;
		if (!isReceptIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isReceptIdValid");
		return isReceptIdValid;
	}

	@Override
	public boolean isReceptNameValid(String receptName) {
		if (receptName == null) return false;
		boolean isReceptNameValid = receptName.length() >= 2 && receptName.length() <= 20;
		if (!isReceptNameValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isReceptNameValid");
		return isReceptNameValid;
	}

	@Override
	public boolean isReceptStatusValid(int status) {
		boolean isReceptStatusValid = status == 0 || status == 1;
		if (!isReceptStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isReceptStatusValid");
		return isReceptStatusValid;
	}

	@Override
	public boolean isReceptValid(ReceptDTO receptDTO) {
		return isReceptIdValid(receptDTO.getReceptId()) && isReceptNameValid(receptDTO.getReceptName()) && isReceptStatusValid(receptDTO.getStatus());
	}

}