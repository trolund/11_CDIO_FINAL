package final_cdio_11.java.data.dto;

/*
 * Recept data transfer object.
 * This class is used to store information about database entities.
 */
public class ReceptDTO {

	private int receptId;
	private String receptName;
	private int status;

	public ReceptDTO(int receptId, String receptName, int status) {
		this.receptId = receptId;
		this.receptName = receptName;
		this.status = status;
	}

	public int getReceptId() {
		return receptId;
	}

	public String getReceptName() {
		return receptName;
	}

	public int getStatus() {
		return status;
	}

	public void setReceptId(int receptId) {
		this.receptId = receptId;
	}

	public void setReceptName(String receptName) {
		this.receptName = receptName;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReceptDTO [receptId=" + receptId + ", receptName=" + receptName + ", status=" + status + "]";
	}

}