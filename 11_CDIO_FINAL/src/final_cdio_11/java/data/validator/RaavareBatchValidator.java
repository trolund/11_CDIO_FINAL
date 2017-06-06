package final_cdio_11.java.data.validator;

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
	public boolean isAmountValid(int amount) {
		return amount >= 0 && amount <= 99999999;
	}

	@Override
	public boolean isRbStatusValid(int status) {
		return status == 0 || status == 1;
	}

}