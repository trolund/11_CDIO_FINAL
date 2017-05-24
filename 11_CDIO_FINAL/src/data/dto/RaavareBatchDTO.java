package data.dto;

/*
 * RaavareBatch data transfer object.
 * This class is used to store information about database entities.
 */
public class RaavareBatchDTO {

	private int rBId;
	private int raavareId;
	private double amount;

	public RaavareBatchDTO(int rBId, int raavareId, double amount) {
		this.rBId = rBId;
		this.raavareId = raavareId;
		this.amount = amount;
	}

	public int getrBId() {
		return rBId;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "RaavareBatchDTO [" + rBId + ", " + raavareId + ", " + amount + "]";
	}

}