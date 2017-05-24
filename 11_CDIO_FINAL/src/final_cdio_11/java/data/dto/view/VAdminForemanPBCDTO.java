package final_cdio_11.java.data.dto.view;

public class VAdminForemanPBCDTO {

	private int oprId;
	private String oprName;
	private int pbId;
	private int rbid;
	private double tara;
	private double netto;
	private int status;

	public VAdminForemanPBCDTO(int oprId, String oprName, int pbId, int rbid, double tara, double netto, int status) {
		this.oprId = oprId;
		this.oprName = oprName;
		this.pbId = pbId;
		this.rbid = rbid;
		this.tara = tara;
		this.netto = netto;
		this.status = status;
	}

	public int getOprId() {
		return oprId;
	}

	public String getOprName() {
		return oprName;
	}

	public int getPbId() {
		return pbId;
	}

	public int getRbid() {
		return rbid;
	}

	public double getTara() {
		return tara;
	}

	public double getNetto() {
		return netto;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "VAdminForemanPBCDTO [oprId=" + oprId + ", oprName=" + oprName + ", pbId=" + pbId + ", rbid=" + rbid + ", tara=" + tara + ", netto=" + netto + ", status=" + status + "]";
	}

}