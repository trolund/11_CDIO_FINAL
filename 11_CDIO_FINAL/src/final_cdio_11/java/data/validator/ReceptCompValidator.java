package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ReceptComponentDTO;

public class ReceptCompValidator implements IReceptCompValidator {

	@Override
	public boolean isValidReceptId(int receptId) {
		return receptId >= 1 && receptId <= 99999999;
	}

	@Override
	public boolean isValidRaavareId(int raavareId) {
		return raavareId >= 1 && raavareId <= 99999999;
	}

	@Override
	public boolean isValidNomNetto(double nomNetto) {
		return nomNetto >= 0.05 && nomNetto <= 20.0;
	}

	@Override
	public boolean isValidTolerance(double tolerance) {
		return tolerance >= 0.1 && tolerance <= 10.0;
	}

	@Override
	public boolean isValidStatus(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isRcValid(ReceptComponentDTO rcDTO) {
		return false;
	}

}