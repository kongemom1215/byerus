package dao.god;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderDao {
	private static OrderDao instance;
	private OrderDao() {}
	public static OrderDao getInstance() {
		if (instance == null) {	
			instance = new OrderDao();		
			}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
			}
		return conn;
	}
	public List<Order> select(int sid) throws SQLException {
		List<Order> orderlist=new ArrayList<Order>();
		String sql="select * from order_tb where sid=? order by oid";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(sid);
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
				
				orderlist.add(order);
			}
		} catch (Exception e) {
			System.out.println("Order select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return orderlist;
	}
	public Order select(String odate, int oid) throws SQLException {
		Order order=new Order();
		System.out.println("odate -> "+odate);
		System.out.println("oid -> "+oid);
		String sql="select * from order_tb where to_char(odate, 'YYYYMMDD')=? and oid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, odate);
			pstmt.setInt(2, oid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(rs.getInt(3));
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
			}
		} catch (Exception e) {
			System.out.println("order select()->"+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return order;
	}
	
	public Order select2(int oid) throws SQLException {
		Order order=new Order();
		String sql="select * from order_tb where oid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(rs.getInt(3));
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
			}
		} catch (Exception e) {
			System.out.println("order select2()->"+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return order;
	}
	public int updateOstate(int oid) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update order_tb set ostate=ostate+1 where oid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateOstate() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	public List<Order> select() throws SQLException {
		List<Order> orderlist=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select o.*, s.sname from order_tb o, shoppinguser s where s.sid=o.sid order by oid desc";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(rs.getInt(3));
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
				order.setSname(rs.getString(13));
				
				orderlist.add(order);
			}
		} catch (Exception e) {
			System.out.println("order select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return orderlist;
	}
	public List<Order> select(String ostate) throws SQLException {
		List<Order> orderlist=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select o.*, s.sname from order_tb o, shoppinguser s where s.sid=o.sid and ostate=? order by oid desc";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ostate);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(rs.getInt(3));
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
				order.setSname(rs.getString(13));
				
				orderlist.add(order);
			}
		} catch (Exception e) {
			System.out.println("order select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return orderlist;
	}
	public List<Order> select(String odate1, String odate2) throws SQLException {
		List<Order> orderlist=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select o.*, s.sname from order_tb o, shoppinguser s where s.sid=o.sid and o.odate between to_date(?) and to_date(?) order by oid desc";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, odate1);
			pstmt.setString(2, odate2);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(rs.getInt(3));
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
				order.setSname(rs.getString(13));
				
				orderlist.add(order);
			}
		} catch (Exception e) {
			System.out.println("order select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return orderlist;
	}
	
	public List<Order> selectPid(int pid) throws SQLException {
		List<Order> orderlist=new ArrayList<Order>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select o.*, s.sname from order_tb o, shoppinguser s where s.sid=o.sid and o.oid in (select o.oid from order_tb o, orderdetail odt where odt.pid=? and odt.oid=o.oid)order by oid desc";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,pid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Order order=new Order();
				order.setOid(rs.getInt(1));
				order.setOdate(rs.getTimestamp(2));
				order.setSid(rs.getInt(3));
				order.setCid(rs.getInt(4));
				order.setOname(rs.getString(5));
				order.setOcontact(rs.getString(6));
				order.setOaddress(rs.getString(7));
				order.setOpost(rs.getInt(8));
				order.setOpay(rs.getInt(9));
				order.setOstate(rs.getInt(10));
				order.setOamount(rs.getInt(11));
				order.setOdelivery(rs.getInt(12));
				order.setSname(rs.getString(13));
				
				orderlist.add(order);
			}
		} catch (Exception e) {
			System.out.println("order select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return orderlist;
	}
	public double[] selectSales(String[] sixdate) throws SQLException {
		double[] salesAmount=new double[6];
		String sql="select sum(oamount-odelivery) from order_tb where to_char(odate,'YY/MM/DD') = ? and ostate>0";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			for(int i=0; i<6; i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, sixdate[i]);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1)==null)
						salesAmount[i]=0;
					else
						salesAmount[i]=rs.getInt(1)/10000.0;
				}
				rs.close();
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("selectSales() -> "+e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
		}
		
		return salesAmount;
	}
	public double[] selectSales2(String[] sixdate) throws SQLException {
		double[] salesAmount=new double[6];
		String sql="select sum(oamount-odelivery) from order_tb where to_char(odate,'YY/MM') = ? and ostate>0";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			for(int i=0; i<6; i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, sixdate[i]);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1)==null)
						salesAmount[i]=0;
					else
						salesAmount[i]=rs.getInt(1)/10000.0;
					System.out.println("salesAmount[i] -> "+salesAmount[i]);
				}
				rs.close();
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("selectSales() -> "+e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
		}
		
		return salesAmount;
	}
	public double[] selectSales3(String[] sixdate) throws SQLException {
		double[] salesAmount=new double[6];
		String sql="select sum(oamount-odelivery) from order_tb where to_char(odate,'YYYY') = ? and ostate>0";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			for(int i=0; i<6; i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, sixdate[i]);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString(1)==null)
						salesAmount[i]=0;
					else
						salesAmount[i]=rs.getInt(1)/10000.0;
					System.out.println("salesAmount[i] -> "+salesAmount[i]);
				}
				rs.close();
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("selectSales() -> "+e.getMessage());
		} finally {
			if(conn!=null)
				conn.close();
		}
		
		return salesAmount;
	}
}
