package final_cdio_11.java.data.validator;

public class ProductBatchCompValidator implements IProductBatchCompValidator {

	@Override
	public boolean isPbIdValid(int pbId) {
		return pbId >= 1 && pbId <= 99999999;
	}

	@Override
	public boolean isRbIdValid(int rbId) {
		return rbId >= 1 && rbId <= 99999999;
	}

	@Override
	public boolean isTareValid(double tare) {
		return !Double.isNaN(tare);
	}

	@Override
	public boolean isNettoValid(double netto) {
		return !Double.isNaN(netto);
	}

	@Override
	public boolean isOprIdValid(int oprId) {
		return oprId >= 1 && oprId <= 99999999;
	}

	@Override
	public boolean isPbcStatusValid(int status) {
		return status == 0 | status == 1;
	}

}