package final_cdio_11.java.data.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import final_cdio_11.java.data.dto.OperatorDTO;

public class OperatorValidator implements IOperatorValidator {

	@Override
	public boolean isOprIdValid(int oprId) {
		return oprId >= 1 && oprId <= 99999999;
	}

	@Override
	public boolean isOprNameValid(String oprName) {
		if (oprName == null) return false;
		return oprName.length() >= 2 && oprName.length() <= 20;
	}

	@Override
	public boolean isOprIniValid(String oprIni) {
		if (oprIni == null) return false;
		return oprIni.length() >= 2 && oprIni.length() <= 4;
	}

	@Override
	public boolean isOprEmailValid(String oprEmail) {
		if (oprEmail == null) return false;
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(oprEmail);
			emailAddr.validate();
		} catch (AddressException e) {
			result = false;
		}
		return result;
	}

	private boolean isDateValid(String date) {
		if (date == null) return false;
		SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
		try {
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	@Override
	public boolean isOprCprValid(String oprCpr) {
		if (oprCpr == null) return false;
		if (oprCpr.length() != 10) return false;
		String date = oprCpr.substring(0, 6);
		return oprCpr.length() == 10 && isDateValid(date);
	}

	@Override
	public boolean isOprPasswordValid(String oprPassword) {
		if (oprPassword == null) return false;
		return oprPassword.length() >= 5 && oprPassword.length() <= 30;
	}

	@Override
	public boolean isOprStatusValid(int status) {
		return status == 0 || status == 1;
	}

	@Override
	public boolean isOprValid(OperatorDTO oprDTO) {
		return isOprIdValid(oprDTO.getOprId()) && isOprNameValid(oprDTO.getOprFirstName()) && isOprNameValid(oprDTO.getOprLastName()) && isOprIniValid(oprDTO.getOprIni()) && isOprEmailValid(oprDTO.getOprEmail()) && isOprCprValid(oprDTO.getOprCpr()) && isOprPasswordValid(oprDTO.getOprPassword()) && isOprStatusValid(oprDTO.getStatus());
	}

}