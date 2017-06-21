package final_cdio_11.java.data.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import final_cdio_11.java.data.dto.OperatorDTO;
import final_cdio_11.java.utils.Utils;

/*
 * Data transfer object validator class.
 * This class implements various validation methods
 * to ensure data integrity.
 */
public class OperatorValidator implements IOperatorValidator {

	private final Utils utils = Utils.getInstance();

	@Override
	public boolean isOprIdValid(int oprId) {
		boolean isOprIdValid = oprId >= 1 && oprId <= 99999999;
		if (!isOprIdValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprIdValid");
		return isOprIdValid;
	}

	@Override
	public boolean isOprNameValid(String oprName) {
		if (oprName == null) return false;
		boolean isOprNameValid = oprName.length() >= 2 && oprName.length() <= 20;
		if (!isOprNameValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprNameValid");
		return isOprNameValid;
	}

	@Override
	public boolean isOprIniValid(String oprIni) {
		if (oprIni == null) return false;
		boolean isOprIniValid = oprIni.length() >= 2 && oprIni.length() <= 4;
		if (!isOprIniValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprIniValid");
		return isOprIniValid;
	}

	@Override
	public boolean isOprEmailValid(String oprEmail) {
		if (oprEmail == null) return false;
		boolean isOprEmailValid = true;
		try {
			InternetAddress emailAddr = new InternetAddress(oprEmail);
			emailAddr.validate();
		} catch (AddressException e) {
			isOprEmailValid = false;
		}
		if (!isOprEmailValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprEmailValid");
		return isOprEmailValid;
	}

	private boolean isDateValid(String date) {
		if (date == null) return false;
		SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
		try {
			df.parse(date);
			return true;
		} catch (ParseException e) {
			if (utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isDateValid");
			return false;
		}
	}

	@Override
	public boolean isOprCprValid(String oprCpr) {
		if (oprCpr == null) return false;
		if (oprCpr.length() != 10) return false;
		String date = oprCpr.substring(0, 6);
		boolean isOprCprValid = oprCpr.length() == 10 && isDateValid(date);
		if (!isOprCprValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprCprValid");
		return isOprCprValid;
	}

	@Override
	public boolean isOprPasswordHashValid(String oprPasswordHash) {
		if (oprPasswordHash == null) return false;
		boolean isOprPasswordHashValid = oprPasswordHash.length() == 64;
		if (!isOprPasswordHashValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprPasswordValid");
		return isOprPasswordHashValid;
	}

	@Override
	public boolean isOprStatusValid(int status) {
		boolean isOprStatusValid = status == 0 || status == 1;
		if (!isOprStatusValid && utils.DEV_ENABLED) utils.logMessage("[" + getClass().getSimpleName() + "] Invalid: isOprStatusValid");
		return isOprStatusValid;
	}

	@Override
	public boolean isOprValid(OperatorDTO oprDTO) {
		return isOprIdValid(oprDTO.getOprId()) && isOprNameValid(oprDTO.getOprFirstName()) && isOprNameValid(oprDTO.getOprLastName()) && isOprIniValid(oprDTO.getOprIni()) && isOprEmailValid(oprDTO.getOprEmail()) && isOprCprValid(oprDTO.getOprCpr()) && isOprPasswordHashValid(oprDTO.getOprPassword()) && isOprStatusValid(oprDTO.getStatus());
	}

}