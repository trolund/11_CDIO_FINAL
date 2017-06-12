package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareBatchDTO;

public interface IRaavareBatchValidator {
	boolean isRbIdValid(int rbId);
	boolean isRaavareIdValid(int raavareId);
	boolean isAmountValid(double amount);
	boolean isRbStatusValid(int status);
	boolean isRbValid(RaavareBatchDTO rbDTO);
}