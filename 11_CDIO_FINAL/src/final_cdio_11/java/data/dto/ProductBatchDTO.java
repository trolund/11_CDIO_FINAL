package final_cdio_11.java.data.dto;

/*
 * ProductBatch data transfer object.
 * This class is used to store information about database entities.
 */
public class ProductBatchDTO {

	private int pbId;
	private int itemStatus;
	private int receptId;
	private int status;

	public ProductBatchDTO(int pbId, int itemStatus, int receptId, int status) {
		this.pbId = pbId;
		this.itemStatus = itemStatus;
		this.receptId = receptId;
		this.status = status;
	}

	public int getpbId() {
		return pbId;
	}

	public int getItemStatus() {
		return itemStatus;
	}

	public int getReceptId() {
		return receptId;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "ProductBatchDTO [pbId=" + pbId + ", itemStatus=" + itemStatus + ", receptId=" + receptId + ", status=" + status + "]";
	}

}