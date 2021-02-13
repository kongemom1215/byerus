package dao.water;

import java.util.Date;

public class Review {
	private int rid;
	private int sid;
	private int oid;
	private int pid;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	private String rwriter;
	private String rtitle;
	private String rcontent;
	private String rimg;
	private Date rdate;
	private int rhit;
	private String rcmt;
	private Date rcmtdate;
	private Date odate;
	// 조회용
		private String sname;
	
	
	public String getSname() {
			return sname;
		}
		public void setSname(String sname) {
			this.sname = sname;
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
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
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
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public Date getRcmtdate() {
		return rcmtdate;
	}
	public void setRcmtdate(Date rcmtdate) {
		this.rcmtdate = rcmtdate;
	}
}
