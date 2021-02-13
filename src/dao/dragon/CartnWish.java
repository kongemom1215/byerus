package dao.dragon;

public class CartnWish {
	private int cwid;
	private int sid;
	private int pid;
	private String cwtype;
	private int cwqty;
	private String pname;
	private int pprice;
	private String cwoption;
	
	
	public String getCwoption() {
		return cwoption;
	}
	public void setCwoption(String cwoption) {
		this.cwoption = cwoption;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	
	
	
	public int getCwid() {
		return cwid;
	}
	public void setCwid(int cwid) {
		this.cwid = cwid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCwtype() {
		return cwtype;
	}
	public void setCwtype(String swtype) {
		this.cwtype = swtype;
	}
	public int getCwqty() {
		return cwqty;
	}
	public void setCwqty(int cwqty) {
		this.cwqty = cwqty;
	}
	
	
}
