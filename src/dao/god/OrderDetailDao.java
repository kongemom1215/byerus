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

public class OrderDetailDao {
	private static OrderDetailDao instance;
	private OrderDetailDao() {}
	public static OrderDetailDao getInstance() {
		if (instance == null) {	
			instance = new OrderDetailDao();		
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
	public List<OrderDetail> select(int oid) throws SQLException {
		List<OrderDetail> orderdetaillist=new ArrayList<OrderDetail>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select o.*, p.pname, p.pprice, p.pthumbimg from orderdetail o, product p where o.oid=? and o.pid=p.pid";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, oid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				OrderDetail detail=new OrderDetail();
				detail.setOid(rs.getInt(1));
				detail.setPid(rs.getInt(2));
				detail.setDqty(rs.getInt(3));
				detail.setPoption(rs.getString(4));
				detail.setPname(rs.getString(6));
				detail.setPprice(rs.getInt(7));
				detail.setPthumbimg(rs.getString(8));
				
				orderdetaillist.add(detail);
			}
		} catch (Exception e) {
			System.out.println("OrderDetail select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return orderdetaillist;
	}
}
