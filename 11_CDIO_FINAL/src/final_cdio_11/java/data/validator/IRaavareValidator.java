package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareDTO;

public interface IRaavareValidator {
	boolean isRaavareIdValid(int raavareId);
	boolean isRaavareNameValid(String raavareName);
	boolean isSupplierValid(String supplier);
	boolean isRaavareStatusValid(int status);
	boolean isRaavareValid(RaavareDTO raavareDTO);
}