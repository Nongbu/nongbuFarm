package veggie.model.vo;

import java.sql.Date;

public class Product {
private static final long serialVersionUID = 10L;
	
	private int proNum;
	private String proID;
	private int proCtgr;
	private String proName;
	private Date proDate;
	private String proOrgin;
	private int proPrice;
	
	public Product() {}

	public Product(int proNum, String proID, int proCtgr, String proName, Date proDate, String proOrgin, int proPrice) {
		super();
		this.proNum = proNum;
		this.proID = proID;
		this.proCtgr = proCtgr;
		this.proName = proName;
		this.proDate = proDate;
		this.proOrgin = proOrgin;
		this.proPrice = proPrice;
	}

	public int getProNum() {
		return proNum;
	}

	public void setProNum(int proNum) {
		this.proNum = proNum;
	}

	public String getProID() {
		return proID;
	}

	public void setProID(String proID) {
		this.proID = proID;
	}

	public int getProCtgr() {
		return proCtgr;
	}

	public void setProCtgr(int proCtgr) {
		this.proCtgr = proCtgr;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Date getProDate() {
		return proDate;
	}

	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}

	public String getProOrgin() {
		return proOrgin;
	}

	public void setProOrgin(String proOrgin) {
		this.proOrgin = proOrgin;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [proNum=" + proNum + ", proID=" + proID + ", proCtgr=" + proCtgr + ", proName=" + proName
				+ ", proDate=" + proDate + ", proOrgin=" + proOrgin + ", proPrice=" + proPrice + "]";
	}

	
	
}
