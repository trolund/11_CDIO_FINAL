package final_cdio_11.java.data.validator;

import final_cdio_11.java.data.dto.OperatorDTO;

public interface IOperatorValidator {
	boolean isOprIdValid(int oprId);
	boolean isOprNameValid(String oprName);
	boolean isOprIniValid(String oprIni);
	boolean isOprEmailValid(String oprEmail);
	boolean isOprCprValid(String oprCpr);
	boolean isOprPasswordHashValid(String oprPassword);
	boolean isOprStatusValid(int status);
	boolean isOprValid(OperatorDTO oprDTO);
}