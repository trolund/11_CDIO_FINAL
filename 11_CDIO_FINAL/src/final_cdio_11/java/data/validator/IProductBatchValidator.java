package final_cdio_11.java.data.validator;

public interface IProductBatchValidator {
	boolean isPbIdValid(int pbId);
	boolean isItemStatusValid(int itemStatus);
	boolean isReceptIdValid(int receptId);
	boolean isPbStatusValid(int status);
}