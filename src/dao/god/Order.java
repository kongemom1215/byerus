package dao.god;

import java.sql.Timestamp;
import java.util.Date;

public class Order {
	private int oid;
	private Timestamp odate;
	private int sid;
	private int cid;
	private String oname;
	private String ocontact;
	private String oaddress;
	private int opost;
	private int opay;
	private int ostate;
	private int oamount;
	private int odelivery;
	//조회용
	private String sname;
	
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	
	public Timestamp getOdate() {
		return odate;
	}
	public void setOdate(Timestamp odate) {
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
	public int getOdelivery() {
		return odelivery;
	}
	public void setOdelivery(int odelivery) {
		this.odelivery = odelivery;
	}
}
