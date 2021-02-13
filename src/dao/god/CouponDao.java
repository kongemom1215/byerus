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

public class CouponDao {
	private static CouponDao instance;
	private CouponDao() {}
	public static CouponDao getInstance() {
		if (instance == null) {	
			instance = new CouponDao();		
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
	
	public String[] selectSid() throws SQLException {
		String[] selectSids=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select sid from shoppinguser where stype=1 order by sid";
		String sql2="select count(*) from shoppinguser where stype=1";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			if(rs.next())
				selectSids=new String[rs.getInt(1)];
			rs.close();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			int i=0;
			while(rs.next()) {
				selectSids[i]=rs.getString(1);
				i++;
			}
		} catch (Exception e) {
			System.out.println("selectSid() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return selectSids;
	}
	public int makeCoupons(String[] selectSids, String cstartdate, String cenddate, int cdiscount, String couponimg) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into coupon values((select nvl(max(cid)+1,0) from coupon),?,to_date(?,'YYYYMMDD'),to_date(?,'YYYYMMDD'),?,null,?)";
		try {
			conn=getConnection();
			for(int i=0;i<selectSids.length; i++) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, selectSids[i]);
				pstmt.setString(2, cstartdate);
				pstmt.setString(3, cenddate);
				pstmt.setInt(4, cdiscount);
				pstmt.setString(5, couponimg);
				
				result=pstmt.executeUpdate();
				pstmt.close();
			}
		} catch (Exception e) {
			System.out.println("makeCoupons() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	
	public List<Coupon> select(int sid) throws SQLException{
		List<Coupon> couponlist=new ArrayList<Coupon>();
		String sql="select * from coupon where sid=? order by cid";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Coupon coupon=new Coupon();
				coupon.setCid(rs.getInt(1));
				coupon.setSid(rs.getInt(2));
				coupon.setCstartdate(rs.getDate(3));
				coupon.setCenddate(rs.getDate(4));
				coupon.setCdiscount(rs.getInt(5));
				coupon.setCusedate(rs.getDate(6));
				coupon.setCouponimg(rs.getString(7));
				couponlist.add(coupon);
			}
		} catch (Exception e) {
			System.out.println("coupon select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return couponlist;
	}
}
