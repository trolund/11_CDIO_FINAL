package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ReceptDTO;

public class ReceptValidator implements IReceptValidator {

	@Override
	public boolean isReceptIdValid(int receptId) {
		return receptId >= 1 && receptId <= 99999999;
	}

	@Override
	public boolean isReceptNameValid(String receptName) {
		if (receptName == null) return false;
		return receptName.length() >= 2 && receptName.length() <= 20;
	}

	@Override
	public boolean isReceptStatusValid(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isReceptValid(ReceptDTO receptDTO) {
		return isReceptIdValid(receptDTO.getReceptId()) && isReceptNameValid(receptDTO.getReceptName()) && isReceptStatusValid(receptDTO.getStatus());
	}

}