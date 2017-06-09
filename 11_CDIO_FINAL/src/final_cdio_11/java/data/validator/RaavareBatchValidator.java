package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareBatchDTO;

public class RaavareBatchValidator implements IRaavareBatchValidator {

	@Override
	public boolean isRbIdValid(int rbId) {
		return rbId >= 1 && rbId <= 99999999;
	}

	@Override
	public boolean isRaavareIdValid(int raavareId) {
		return raavareId >= 1 && raavareId <= 99999999;
	}

	@Override
	public boolean isAmountValid(double amount) {
		return amount >= 0 && amount <= 99999999;
	}

	@Override
	public boolean isRbStatusValid(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isRbValid(RaavareBatchDTO rbDTO) {
		return isRbIdValid(rbDTO.getRbId()) && isRaavareIdValid(rbDTO.getRaavareId()) && isAmountValid(rbDTO.getAmount()) && isRbStatusValid(rbDTO.getStatus());
	}

}