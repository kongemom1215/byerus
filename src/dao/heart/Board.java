package dao.heart;

import java.sql.Date;

public class Board {
	
	// QNA
	private int qid;
	//private int sid;
	private String qctg;
	private int pid;
	//private int oid;
	private String qcontent;
	private Date qdate;
	private String qfile;
	private String qcmt;
	private Date qcmtdate;

	private Date odate;
	private int qpwd;
	private String pname;
	
		// REVIEW
	private int rid;
	private int sid;
	private int oid;
	private String rwriter;
	private String rtitle;
	private String rcontent;
	private String rimg;
	private Date rdate;
	private int rhit;
	private String rcmt;
	private Date rcmtwriter;
	private Date rcmtdate;
	//private Date odate;
	
	private String sname;
	
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getQctg() {
		return qctg;
	}
	public void setQctg(String qctg) {
		this.qctg = qctg;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public Date getQdate() {
		return qdate;
	}
	public void setQdate(Date qdate) {
		this.qdate = qdate;
	}
	public String getQfile() {
		return qfile;
	}
	public void setQfile(String qfile) {
		this.qfile = qfile;
	}
	public String getQcmt() {
		return qcmt;
	}
	public void setQcmt(String qcmt) {
		this.qcmt = qcmt;
	}
	public Date getQcmtdate() {
		return qcmtdate;
	}
	public void setQcmtdate(Date qcmtdate) {
		this.qcmtdate = qcmtdate;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
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
	public Date getRcmtwriter() {
		return rcmtwriter;
	}
	public void setRcmtwriter(Date rcmtwriter) {
		this.rcmtwriter = rcmtwriter;
	}
	
	public int getQpwd() {
		return qpwd;
	}
	public void setQpwd(int qpwd) {
		this.qpwd = qpwd;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Date getRcmtdate() {
		return rcmtdate;
	}
	public void setRcmtdate(Date rcmtdate) {
		this.rcmtdate = rcmtdate;
	}

}
