package dao.heart;

import java.util.Date;
//Order_tb + OrderDetail + Product => Join한 DTO
public class Order_Join {

	private int oid;
	private Date odate;
	private int sid;

	private int cid;
	private String oname;
	private String ocontact;
	private String oaddress;
	private int opost;
	private int opay;
	private int ostate;
	private int oamount;
	private int oinvoice;
	private int odelivery;
	private int pid;
	private int dqty;
	private String pname;
	private String pthumbimg;
	private String poption;
	private int cdiscount;
	private int reviewox;
	
	public int getReviewox() {
		return reviewox;
	}
	public void setReviewox(int reviewox) {
		this.reviewox = reviewox;
	}
	public int getCdiscount() {
		return cdiscount;
	}
	public void setCdiscount(int cdiscount) {
		this.cdiscount = cdiscount;
	}
	public String getPoption() {
		return poption;
	}
	public void setPoption(String poption) {
		this.poption = poption;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPthumbimg() {
		return pthumbimg;
	}
	public void setPthumbimg(String pthumbimg) {
		this.pthumbimg = pthumbimg;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getOcontact() {
		return ocontact;
	}
	public void setOcontact(String ocontact) {
		this.ocontact = ocontact;
	}
	public String getOaddress() {
		return oaddress;
	}
	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}
	public int getOpost() {
		return opost;
	}
	public void setOpost(int opost) {
		this.opost = opost;
	}
	public int getOpay() {
		return opay;
	}
	public void setOpay(int opay) {
		this.opay = opay;
	}
	public int getOstate() {
		return ostate;
	}
	public void setOstate(int ostate) {
		this.ostate = ostate;
	}
	public int getOamount() {
		return oamount;
	}
	public void setOamount(int oamount) {
		this.oamount = oamount;
	}
	public int getOinvoice() {
		return oinvoice;
	}
	public void setOinvoice(int oinvoice) {
		this.oinvoice = oinvoice;
	}
	public int getOdelivery() {
		return odelivery;
	}
	public void setOdelivery(int odelivery) {
		this.odelivery = odelivery;
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
