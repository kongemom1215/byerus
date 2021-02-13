package dao.god;

import java.util.Date;

public class Coupon {
	private int cid;
	private int sid;
	private Date cstartdate;
	private Date cenddate;
	private int cdiscount;
	private Date cusedate;
	private String couponimg;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getCstartdate() {
		return cstartdate;
	}
	public void setCstartdate(Date cstartdate) {
		this.cstartdate = cstartdate;
	}
	public Date getCenddate() {
		return cenddate;
	}
	public void setCenddate(Date cenddate) {
		this.cenddate = cenddate;
	}
	public int getCdiscount() {
		return cdiscount;
	}
	public void setCdiscount(int cdiscount) {
		this.cdiscount = cdiscount;
	}
	public Date getCusedate() {
		return cusedate;
	}
	public void setCusedate(Date cusedate) {
		this.cusedate = cusedate;
	}
	public String getCouponimg() {
		return couponimg;
	}
	public void setCouponimg(String couponimg) {
		this.couponimg = couponimg;
	}
}
