package final_cdio_11.java.data.validator;

public interface IReceptValidator {
	boolean isReceptIdValid(int receptId);
	boolean isReceptNameValid(String receptName);
	boolean isReceptStatusValid(int status);
}