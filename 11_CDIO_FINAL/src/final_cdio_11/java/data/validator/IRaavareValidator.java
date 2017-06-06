package final_cdio_11.java.data.validator;

public interface IRaavareValidator {
	boolean isRaavareIdValid(int raavareId);
	boolean isRaavareNameValid(String raavareName);
	boolean isSupplierValid(String supplier);
	boolean isRaavareStatusValid(int status);
}