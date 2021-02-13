package dao.heart;

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
		if(instance == null) {
			instance = new CouponDao();
		}
		return instance;
	}
	private Connection getConnection() {
		Connection conn = null;
		
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	public Coupon select(int sid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from coupon where sid= ?";
		
		System.out.println("Coupon select sql -> "+ sql);
		Coupon coupon = new Coupon();
		try {
			conn = getConnection();
			System.out.println("Coupon select 1");
			pstmt = conn.prepareStatement(sql);
			System.out.println("Coupon select 2");
			pstmt.setInt(1, sid);
			System.out.println("Coupon select 3");
			rs = pstmt.executeQuery();
			System.out.println("Coupon select 4");
			while(rs.next()) {
				coupon.setCid(rs.getInt("cid"));
				coupon.setSid(rs.getInt("sid"));
				coupon.setCstartdate(rs.getDate("cstartdate"));
				coupon.setCenddate(rs.getDate("cenddate"));
				coupon.setCdiscount(rs.getInt("cdiscount"));
				coupon.setCusedate(rs.getDate("cusedate"));
				coupon.setCouponimg(rs.getString("couponimg"));
			}
		} catch (Exception e) {
			System.out.println("CouponDao select error=>"+e.getMessage());
		} finally {
			if (rs !=null)    rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null)  conn.close();
		}
		return coupon;
	}
	public int getCount(int sid) throws SQLException {
		ResultSet rs =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select count(*) from coupon where sid=? and cenddate >= sysdate and cusedate is null order by cenddate ";
		int cnt =0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) cnt=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("CouponDao getCount Error->"+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
	    }
		System.out.println("DAO getCount cnt->"+cnt);
	   return cnt;
	}
	
	public List<Coupon> list(int sid,int startRow, int endRow) throws SQLException {
		List<Coupon> listC= new ArrayList<Coupon>();
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 String sql = "select * from (select rownum rn, q.* from (select * from coupon where sid=? and cenddate >= sysdate and cusedate is null order by cenddate) q) where rn between ? and ?";
			System.out.println("Coupon list sql -> "+ sql);
		 try {
				conn = getConnection();
				System.out.println("Coupon list 1");
				pstmt = conn.prepareStatement(sql);
				System.out.println("Coupon list 2");
				pstmt.setInt(1, sid);
				System.out.println("Coupon list 3");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				System.out.println("startRow->"+startRow);
				System.out.println("endRow"+endRow);
				rs = pstmt.executeQuery();
				System.out.println("Coupon list 4");
				
				while (rs.next()) {			
					Coupon coupon = new Coupon();
					coupon.setCid(rs.getInt("cid"));
					coupon.setSid(rs.getInt("sid"));
					coupon.setCstartdate(rs.getDate("cstartdate"));
					coupon.setCenddate(rs.getDate("cenddate"));
					coupon.setCdiscount(rs.getInt("cdiscount"));
					coupon.setCusedate(rs.getDate("cusedate"));
					coupon.setCouponimg(rs.getString("couponimg"));
					System.out.println("Coupon list cid->"+rs.getInt("cid"));
					listC.add(coupon);
				}
			} catch(Exception e) {	System.out.println("CouponDao list Error-> "+e.getMessage());
			} finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}
		 System.out.println(listC);
		return listC;
	}
}
