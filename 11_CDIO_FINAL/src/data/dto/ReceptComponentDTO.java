package data.dto;

/*
 * ReceptComponent data transfer object.
 * This class is used to store information about database entities.
 */
public class ReceptComponentDTO {

	private int receptId;
	private int raavareId;
	private double nomNetto;
	private double tolerance;

	public ReceptComponentDTO(int receptId, int raavareId, double nomNetto, double tolerance) {
		this.receptId = receptId;
		this.raavareId = raavareId;
		this.nomNetto = nomNetto;
		this.tolerance = tolerance;
	}

	public int getReceptId() {
		return receptId;
	}

	public int getRaavareId() {
		return raavareId;
	}

	public double getNomNetto() {
		return nomNetto;
	}

	public double getTolerance() {
		return tolerance;
	}

	@Override
	public String toString() {
		return "ReceptComponentDTO [" + receptId + ", " + raavareId + ", " + nomNetto + ", " + tolerance + "]";
	}

}