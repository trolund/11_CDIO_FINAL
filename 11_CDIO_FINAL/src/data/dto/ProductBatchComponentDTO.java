package data.dto;

/*
 * ProductBatchComponent data transfer object.
 * This class is used to store information about database entities.
 */
public class ProductBatchComponentDTO {

	private int pbId;
	private int rbId;
	private double tara;
	private double netto;
	private int oprId;

	public ProductBatchComponentDTO(int pbId, int rbId, double tara, double netto, int oprId) {
		this.pbId = pbId;
		this.rbId = rbId;
		this.tara = tara;
		this.netto = netto;
		this.oprId = oprId;
	}

	public int getpbId() {
		return pbId;
	}

	public int getrbId() {
		return rbId;
	}

	public double getTara() {
		return tara;
	}

	public double getNetto() {
		return netto;
	}

	public int getOprId() {
		return oprId;
	}

	@Override
	public String toString() {
		return "ProductBatchComponentDTO [" + pbId + ", " + rbId + ", " + tara + ", " + netto + ", " + oprId + "]";
	}

}