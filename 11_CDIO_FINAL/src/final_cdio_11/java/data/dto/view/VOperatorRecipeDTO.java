package final_cdio_11.java.data.dto.view;

public class VOperatorRecipeDTO {

	private int raavareId;
	private int receptId;
	private String raavareName;
	private String supplier;
	private String receptName;
	private double nom_netto;
	private double tolerance;

	public VOperatorRecipeDTO(int raavareId, int receptId, String raavareName, String supplier, String receptName, double nom_netto, double tolerance) {
		this.raavareId = raavareId;
		this.receptId = receptId;
		this.raavareName = raavareName;
		this.supplier = supplier;
		this.receptName = receptName;
		this.nom_netto = nom_netto;
		this.tolerance = tolerance;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public int getReceptId() {
		return receptId;
	}

	public String getRaavareName() {
		return raavareName;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getReceptName() {
		return receptName;
	}

	public double getNom_netto() {
		return nom_netto;
	}

	public double getTolerance() {
		return tolerance;
	}

	@Override
	public String toString() {
		return "VOperatorRecipeDTO [raavareId=" + raavareId + ", receptId=" + receptId + ", raavareName=" + raavareName + ", supplier=" + supplier + ", receptName=" + receptName + ", nom_netto=" + nom_netto + ", tolerance=" + tolerance + "]";
	}

}