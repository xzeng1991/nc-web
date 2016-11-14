package db.model;

public class XingzuoInfo extends BaseDomain {
	private static final long serialVersionUID = -4419715208347001277L;

	private int id;
	private String xzName;
	private String xzInfo;
	private String xzStartTime;
	private String xzEndTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getXzName() {
		return xzName;
	}

	public void setXzName(String xzName) {
		this.xzName = xzName;
	}

	public String getXzInfo() {
		return xzInfo;
	}

	public void setXzInfo(String xzInfo) {
		this.xzInfo = xzInfo;
	}

	public String getXzStartTime() {
		return xzStartTime;
	}

	public void setXzStartTime(String xzStartTime) {
		this.xzStartTime = xzStartTime;
	}

	public String getXzEndTime() {
		return xzEndTime;
	}

	public void setXzEndTime(String xzEndTime) {
		this.xzEndTime = xzEndTime;
	}

	@Override
	public String toString() {
		return "XingzuoInfo [id=" + id + ", xzName=" + xzName + ", xzInfo=" + xzInfo + ", xzStartTime=" + xzStartTime
				+ ", xzEndTime=" + xzEndTime + "]";
	}

}
