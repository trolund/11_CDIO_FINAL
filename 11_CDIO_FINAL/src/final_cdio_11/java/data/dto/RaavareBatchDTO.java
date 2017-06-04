package final_cdio_11.java.data.dto;

/*
 * RaavareBatch data transfer object.
 * This class is used to store information about database entities.
 */
public class RaavareBatchDTO {

	private int rbId;
	private int raavareId;
	private double amount;
	private int status;

	public RaavareBatchDTO(int rbId, int raavareId, double amount, int status) {
		this.rbId = rbId;
		this.raavareId = raavareId;
		this.amount = amount;
		this.status = status;
	}

	public int getrbId() {
		return rbId;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public double getAmount() {
		return amount;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RaavareBatchDTO [rbId=" + rbId + ", raavareId=" + raavareId + ", amount=" + amount + ", status=" + status + "]";
	}

}