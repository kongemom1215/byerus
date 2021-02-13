package dao.god;

import java.util.Date;

public class OrderDetail {
	private int oid;
	private int pid;
	private int dqty;
	private String poption;
	//조회용
	private String pname;
	private int pprice;
	private String pthumbimg;
	
	public String getPthumbimg() {
		return pthumbimg;
	}
	public void setPthumbimg(String pthumbimg) {
		this.pthumbimg = pthumbimg;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPoption() {
		return poption;
	}
	public void setPoption(String poption) {
		this.poption = poption;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getDqty() {
		return dqty;
	}
	public void setDqty(int dqty) {
		this.dqty = dqty;
	}
}
