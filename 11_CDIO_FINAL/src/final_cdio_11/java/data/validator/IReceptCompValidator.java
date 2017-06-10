package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.ReceptComponentDTO;

public interface IReceptCompValidator {
	boolean isReceptIdValid(int receptId);
	boolean isRaavareIdValid(int raavareId);
	boolean isNomNettoValid(double nomNetto);
	boolean isToleranceValid(double tolerance);
	boolean isRcStatusValid(int status);
	boolean isRcValid(ReceptComponentDTO rcDTO);
}