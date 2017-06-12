package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ReceptDTO;

public interface IReceptValidator {
	boolean isReceptIdValid(int receptId);
	boolean isReceptNameValid(String receptName);
	boolean isReceptStatusValid(int status);
	boolean isReceptValid(ReceptDTO receptDTO);
}