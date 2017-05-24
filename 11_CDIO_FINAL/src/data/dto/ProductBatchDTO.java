package data.dto;

/*
 * ProductBatch data transfer object.
 * This class is used to store information about database entities.
 */
public class ProductBatchDTO {

	private int pbId;
	private int status;
	private int receptId;

	public ProductBatchDTO(int pbId, int status, int receptId) {
		this.pbId = pbId;
		this.status = status;
		this.receptId = receptId;
	}

	public int getpbId() {
		return pbId;
	}

	public int getStatus() {
		return status;
	}

	public int getReceptId() {
		return receptId;
	}

	@Override
	public String toString() {
		return "ProductBatchDTO [" + pbId + ", " + status + ", " + receptId + "]";
	}

}