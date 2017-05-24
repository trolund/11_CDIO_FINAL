package data.dto;

/*
 * Recept data transfer object.
 * This class is used to store information about database entities.
 */
public class ReceptDTO {

	private int receptId;
	private String receptName;

	public ReceptDTO(int receptId, String receptName) {
		this.receptId = receptId;
		this.receptName = receptName;
	}

	public int getReceptId() {
		return receptId;
	}

	public String getReceptName() {
		return receptName;
	}

	@Override
	public String toString() {
		return "ReceptDTO [" + receptId + ", " + receptName + "]";
	}

}