package dao.god;

import java.sql.Timestamp;

public class Review {
	private int rid;
	private int sid;
	private int oid;
	private String rwriter;
	private String rtitle;
	private String rcontent;
	private String rimg;
	private Timestamp rdate;
	private int rhit;
	private String rcmt;
	private Timestamp rcmtdate;
	private Timestamp odate;
	private int pid;
	//조회용
	private String pname;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getRwriter() {
		return rwriter;
	}
	public void setRwriter(String rwriter) {
		this.rwriter = rwriter;
	}
	public String getRtitle() {
		return rtitle;
	}
	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getRimg() {
		return rimg;
	}
	public void setRimg(String rimg) {
		this.rimg = rimg;
	}
	
	public int getRhit() {
		return rhit;
	}
	public void setRhit(int rhit) {
		this.rhit = rhit;
	}
	public String getRcmt() {
		return rcmt;
	}
	public void setRcmt(String rcmt) {
		this.rcmt = rcmt;
	}
	public Timestamp getRdate() {
		return rdate;
	}
	public void setRdate(Timestamp rdate) {
		this.rdate = rdate;
	}
	public Timestamp getRcmtdate() {
		return rcmtdate;
	}
	public void setRcmtdate(Timestamp rcmtdate) {
		this.rcmtdate = rcmtdate;
	}
	public Timestamp getOdate() {
		return odate;
	}
	public void setOdate(Timestamp odate) {
		this.odate = odate;
	}

}
