package dao.god;

import java.util.Date;

public class Product {

	private int pid;
	private String ptype;
	private String col1;
	private String col2;
	private String col3;
	private int pprice;
	private int pinventory;
	private String pname;
	private Date pregdate;
	private int psell;
	private int pdiscount;
	private int ppublic;
	private String pthumbimg;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getPinventory() {
		return pinventory;
	}

	public void setPinventory(int pinventory) {
		this.pinventory = pinventory;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Date getPregdate() {
		return pregdate;
	}

	public void setPregdate(Date pregdate) {
		this.pregdate = pregdate;
	}

	public int getPsell() {
		return psell;
	}

	public void setPsell(int psell) {
		this.psell = psell;
	}

	public int getPdiscount() {
		return pdiscount;
	}

	public void setPdiscount(int pdiscount) {
		this.pdiscount = pdiscount;
	}

	public int getPpublic() {
		return ppublic;
	}

	public void setPpublic(int ppublic) {
		this.ppublic = ppublic;
	}

	public String getPthumbimg() {
		return pthumbimg;
	}

	public void setPthumbimg(String pthumbimg) {
		this.pthumbimg = pthumbimg;
	}

	public String getPoption() {
		return poption;
	}

	public void setPoption(String poption) {
		this.poption = poption;
	}

	private String poption;

}
