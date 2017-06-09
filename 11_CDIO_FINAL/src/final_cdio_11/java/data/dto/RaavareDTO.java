package final_cdio_11.java.data.dto;

/*
 * Raavare data transfer object.
 * This class is used to store information about database entities.
 */
public class RaavareDTO {

	private int raavareId;
	private String raavareName;
	private String supplier;
	private int status;

	public RaavareDTO(int raavareId, String raavareName, String supplier, int status) {
		this.raavareId = raavareId;
		this.raavareName = raavareName;
		this.supplier = supplier;
		this.status = status;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public String getRaavareName() {
		return raavareName;
	}

	public String getSupplier() {
		return supplier;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "RaavareDTO [raavareId=" + raavareId + ", raavareName=" + raavareName + ", supplier=" + supplier + ", status=" + status + "]";
	}

}