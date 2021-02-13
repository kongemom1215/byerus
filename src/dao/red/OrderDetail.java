package dao.red;



public class OrderDetail {
	private int oid;
	private int pid;
	private int dqty;
	private String poption;
	
	public String getPoption() {
		return poption;
	}
	public void setPoption(String poption) {
		this.poption = poption;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
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
