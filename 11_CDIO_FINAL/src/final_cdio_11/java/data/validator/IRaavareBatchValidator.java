package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.RaavareBatchDTO;

/*
 * Interface for the raavarebatch validator class.
 * This interface contains various validation methods
 * for the RaavareBatchDTO class.
 */
public interface IRaavareBatchValidator {
	boolean isRbIdValid(int rbId);
	boolean isRaavareIdValid(int raavareId);
	boolean isAmountValid(double amount);
	boolean isRbStatusValid(int status);
	boolean isRbValid(RaavareBatchDTO rbDTO);
}