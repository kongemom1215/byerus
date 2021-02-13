package dao.red;

import java.util.Date;

public class Order {
	private int oid;
	private Date odate;
	private int sid;
	private int cid;
	private String oname;
	private String oaddress;
	private int opost;
	private int opay;
	private int ostate;
	private double oamount;
	private int odeliverey;
	private int dqty;
	private String ocontact;
	private int pid;
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
	public double getOamount() {
		return oamount;
	}
	public void setOamount(double oamount) {
		this.oamount = oamount;
	}
	
	public int getOdeliverey() {
		return odeliverey;
	}
	public void setOdeliverey(int odeliverey) {
		this.odeliverey = odeliverey;
	}
	public int getDqty() {
		return dqty;
	}
	public void setDqty(int dqty) {
		this.dqty = dqty;
	}
	public String getOcontact() {
		return ocontact;
	}
	public void setOcontact(String ocontact) {
		this.ocontact = ocontact;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	

}
