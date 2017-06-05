package final_cdio_11.java.data.validator;

public interface IRaavareBatchValidator {
	boolean isRbIdValid(int rbId);
	boolean isRaavareIdValid(int raavareId);
	boolean isAmountValid(int amount);
	boolean isRbStatusValid(int status);
}