package dao.half;

import java.util.Date;

public class Qna {
	private int qid;
	private int sid;
	private String qctg;
	private int pid;
	private int oid;
	private String qcontent;
	private Date qdate;
	private String qfile;
	private String qcmt;
	private Date qcmtdate;
	private Date odate;

	
	

	// 조회용 테이블에 없음!
	private String sname;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
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

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
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

	
	
	
}