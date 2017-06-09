package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareDTO;

public class RaavareValidator implements IRaavareValidator {

	@Override
	public boolean isRaavareIdValid(int raavareId) {
		return raavareId >= 1 && raavareId <= 99999999;
	}

	@Override
	public boolean isRaavareNameValid(String raavareName) {
		if (raavareName == null) return false;
		return raavareName.length() >= 2 && raavareName.length() <= 20;
	}

	@Override
	public boolean isSupplierValid(String supplier) {
		if (supplier == null) return false;
		return supplier.length() >= 2 && supplier.length() <= 20;
	}

	@Override
	public boolean isRaavareStatusValid(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isRaavareValid(RaavareDTO raavareDTO) {
		return false;
	}

}