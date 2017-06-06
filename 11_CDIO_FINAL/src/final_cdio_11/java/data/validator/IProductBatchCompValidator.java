package final_cdio_11.java.data.validator;

public interface IProductBatchCompValidator {
	boolean isPbIdValid(int pbId);
	boolean isRbIdValid(int rbId);
	boolean isTareValid(double tare);
	boolean isNettoValid(double netto);
	boolean isOprIdValid(int oprId);
	boolean isPbcStatusValid(int status);
}