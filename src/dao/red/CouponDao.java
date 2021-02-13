package dao.red;

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
	public Coupon select(int session_sid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select cid,sid,cstartdate,cenddate,cdiscount,cusedate from coupon where sid= ?";
		
		System.out.println("Coupon select sql -> "+ sql);
		Coupon coupon = new Coupon();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, session_sid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				coupon.setCid(rs.getInt("cid"));
				coupon.setSid(rs.getInt("sid"));
				coupon.setCstartdate(rs.getDate("cstartdate"));
				coupon.setCenddate(rs.getDate("cenddate"));
				coupon.setCdiscount(rs.getInt("cdiscount"));
				coupon.setCusedate(rs.getDate("cusedate"));
				System.out.println("coupon=>"+coupon);
			}
		} catch (Exception e) {
			System.out.println("ShoppingUser select error=>"+e.getMessage());
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
		String sql = "select count(*) from coupon order by cenddate where sid=? and cenddate >= sysdate and cusedate is null ";
		int cnt =0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) cnt=rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
	}
		return cnt;
	}
	public int getTotalCnt(int sid) throws SQLException {
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "Select count(*) from coupon where sid=?";
		int tot = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			if(rs.next()) tot= rs.getInt(1);
		} catch (Exception e) {
			System.out.println("CouponDao getTotalCnt => "+e.getMessage());
		}finally {
			if(rs != null) rs.close();
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
		}
		return tot;
	}
	public ArrayList<Coupon> list(int session_sid) throws SQLException {
		ArrayList<Coupon> cu= new ArrayList<Coupon>();
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		 String sql = "select cid,sid,cdiscount from coupon where sid=? and cenddate >= sysdate and cstartDate <=sysdate and cusedate is null ";
		System.out.println("CouponDao 확인1"); 
		try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, session_sid);
				rs = pstmt.executeQuery();
				System.out.println("CouponDao 확인2"); 
				if(rs.next()) {
						do{
	//홍주					
						Coupon coupon = new Coupon();
						System.out.println("CouponDao 확인3"); 
						coupon.setCid(rs.getInt("cid"));
						coupon.setSid(session_sid);
						coupon.setCdiscount(rs.getInt("cdiscount"));
						System.out.println("CouponDao 확인4"); 
//						System.out.println("CouponDao 확인5"); 
//						coupon.setCstartdate(rs.getDate());
//						System.out.println("CouponDao 확인6"); 
//						coupon.setCenddate(rs.getDate(4));
//						System.out.println("CouponDao 확인7"); 
//						
//						System.out.println("CouponDao 확인8"); 
//						coupon.setCusedate(rs.getDate(6));
//						System.out.println("CouponDao 확인9"); 
						cu.add(coupon);
					
				}while(rs.next());
				}
			} catch(Exception e) {	System.out.println(e.getMessage());
			} finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			}
		return cu;
	}
//주문결제시 필요한 쿠폰 가져오기==>홍주 적용
	public ArrayList<Coupon> selectCoupon(int session_sid) throws SQLException{
		ArrayList<Coupon> cu = new ArrayList<Coupon>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select cid,cdiscount,cstartdate,cenddate from coupon "
				+ "where sid=? and cenddate >= sysdate "
				+ "and CSTARTDATE <=sysdate and cusedate is null";

		System.out.println("CouponDao selectCoupon 확인1"); 
		try {
			System.out.println("CouponDao selectCoupon 확인2"); 	
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			System.out.println("CouponDao selectCoupon 확인3"); 
			pstmt.setInt(1, session_sid);
			System.out.println("CouponDao selectCoupon 확인4"); 
			rs=pstmt.executeQuery();
			System.out.println("CouponDao selectCoupon 확인5"); 
			
			if(rs.next()) {
				do {
					System.out.println("CouponDao selectCoupon 확인4"); 
					Coupon coupon = new Coupon();
					coupon.setCid(rs.getInt("cid"));
					coupon.setCdiscount(rs.getInt("cdiscount"));
					coupon.setCstartdate(rs.getDate("cstartdate"));
					coupon.setCenddate(rs.getDate("cenddate"));
					
//					coupon.setSid(rs.getInt("sid"));
//					coupon.setCusedate(rs.getDate("cusedate"));
//					coupon.setCouponimg(rs.getString("couponimg"));					
					System.out.println("CouponDao selectCoupon 확인5"); 
					cu.add(coupon);
				}while(rs.next());
			
			}
			System.out.println("CouponDao 확인6"); 
			
		}catch (Exception e) {
			System.out.println("CouponDao selectCoupon error!!"+e.getMessage());
		}finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		return cu;
	}
//적용할 쿠폰 정보 가져오기
	public int discount (int Vcid) throws SQLException {
		int dis=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select cdiscount from coupon where cid =?";
		
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, Vcid);
			rs=pstmt.executeQuery();
			if(rs.next())
			dis=rs.getInt(1);
			
			
		}catch (Exception e) {
			System.out.println("CouponDao discount error!!"+e.getMessage());
		}finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		
		
		
		return dis;
		
		
	}
//사용한 쿠폰에 사용날짜 입력
	public int checkUsedDate(int cid) throws SQLException {
		int used_couponCheck = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update coupon set CUSEDATE=sysdate where cid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			used_couponCheck = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("CouponDao checkUsedDate error!!!"+e.getMessage());
		}finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
	
		
		return used_couponCheck;
	}
}
