package dao.dragon;

import java.util.Date;

public class ShoppingUser {

	private int sid;
	private int stype;
	private String semail;
	private String spwd;
	private String sname;
	private String scontact;
	private Date sbirthdate;
	private Date sregdate;
	private String saddress;
	private int spost;
	private String sagree;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getScontact() {
		return scontact;
	}

	public void setScontact(String scontact) {
		this.scontact = scontact;
	}

	public Date getSbirthdate() {
		return sbirthdate;
	}

	public void setSbirthdate(Date sbirthdate) {
		this.sbirthdate = sbirthdate;
	}

	public Date getSregdate() {
		return sregdate;
	}

	public void setSregdate(Date sregdate) {
		this.sregdate = sregdate;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public int getSpost() {
		return spost;
	}

	public void setSpost(int spost) {
		this.spost = spost;
	}

	public String getSagree() {
		return sagree;
	}

	public void setSagree(String sagree) {
		this.sagree = sagree;
	}
}
