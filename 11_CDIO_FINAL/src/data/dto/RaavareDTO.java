package data.dto;

/*
 * Raavare data transfer object.
 * This class is used to store information about database entities.
 */
public class RaavareDTO {

	private int raavareId;
	private String raavareName;
	private String supplier;

	public RaavareDTO(int raavareId, String raavareName, String supplier) {
		this.raavareId = raavareId;
		this.raavareName = raavareName;
		this.supplier = supplier;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public String getraavareName() {
		return raavareName;
	}

	public String getSupplier() {
		return supplier;
	}

	@Override
	public String toString() {
		return "RaavareDTO [" + raavareId + ", " + raavareName + ", " + supplier + "]";
	}

}