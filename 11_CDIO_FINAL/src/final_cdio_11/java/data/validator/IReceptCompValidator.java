package final_cdio_11.java.data.validator;

public interface IReceptCompValidator {
	boolean isValidReceptId(int receptId);
	boolean isValidRaavareId(int raavareId);
	boolean isValidNomNetto(double nomNetto);
	boolean isValidTolerance(double tolerance);
	boolean isValidStatus(int status);
}