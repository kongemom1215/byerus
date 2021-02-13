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

public class ReviewDao {
	private static ReviewDao instance;
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		if (instance == null) {	
			instance = new ReviewDao();		
			}
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
			}
		return conn;
	}
	public int totalReview() throws SQLException {
		int totalReview=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from review";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalReview=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("totalReview() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return totalReview;
	}
	public List<Review> list(int startRow, int endRow) throws SQLException {
		List<Review> reviewlist=new ArrayList<Review>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn ,a.* from (select r.*, p.pname from review r, product p, orderdetail o where o.pid=p.pid and r.oid=o.oid order by rid desc) a ) where rn between ? and ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Review review=new Review();
				review.setRid(rs.getInt(2));
				review.setSid(rs.getInt(3));
				review.setOid(rs.getInt(4));
				review.setRwriter(rs.getString(5));
				review.setRtitle(rs.getString(6));
				review.setRcontent(rs.getString(7));
				review.setRimg(rs.getString(8));
				review.setRdate(rs.getTimestamp(9));
				review.setRhit(rs.getInt(10));
				review.setRcmt(rs.getString(11));
				review.setRcmtdate(rs.getTimestamp(12));
				review.setOdate(rs.getTimestamp(13));
				review.setPid(rs.getInt(14));
				review.setPname(rs.getString(15));
				
				
				reviewlist.add(review);
			}
		} catch (Exception e) {
			System.out.println("review list()->"+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return reviewlist;
	}
	public int totalWait() throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int totalWait=0;
		String sql="select count(*) from review where rcmt is null";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalWait=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("totalWait() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return totalWait;
	}
	public List<Review> waitlist(int startRow, int endRow) throws SQLException {
		List<Review> waitlist=new ArrayList<Review>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn ,a.* from (select r.*, p.pname from review r, product p, orderdetail o where o.pid=p.pid and r.oid=o.oid and rcmt is null order by rid desc) a ) where rn between ? and ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Review review=new Review();
				review.setRid(rs.getInt(2));
				review.setSid(rs.getInt(3));
				review.setOid(rs.getInt(4));
				review.setRwriter(rs.getString(5));
				review.setRtitle(rs.getString(6));
				review.setRcontent(rs.getString(7));
				review.setRimg(rs.getString(8));
				review.setRdate(rs.getTimestamp(9));
				review.setRhit(rs.getInt(10));
				review.setRcmt(rs.getString(11));
				review.setRcmtdate(rs.getTimestamp(12));
				review.setOdate(rs.getTimestamp(13));
				review.setPid(rs.getInt(14));
				review.setPname(rs.getString(15));
				
				waitlist.add(review);
			}
		} catch (Exception e) {
			System.out.println("review waitlist() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return waitlist;
	}
	public int totalReview(int pid) throws SQLException {
		int totalReview=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from review where pid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalReview=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("totalReview() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return totalReview;
	}
	public List<Review> list(int startRow, int endRow, int pid) throws SQLException {
		List<Review> reviewlist=new ArrayList<Review>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn ,a.* from (select r.*, p.pname from review r, product p, orderdetail o where o.pid=p.pid and r.oid=o.oid and o.pid=? order by rid desc) a ) where rn between ? and ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Review review=new Review();
				review.setRid(rs.getInt(2));
				review.setSid(rs.getInt(3));
				review.setOid(rs.getInt(4));
				review.setRwriter(rs.getString(5));
				review.setRtitle(rs.getString(6));
				review.setRcontent(rs.getString(7));
				review.setRimg(rs.getString(8));
				review.setRdate(rs.getTimestamp(9));
				review.setRhit(rs.getInt(10));
				review.setRcmt(rs.getString(11));
				review.setRcmtdate(rs.getTimestamp(12));
				review.setOdate(rs.getTimestamp(13));
				review.setPid(rs.getInt(14));
				review.setPname(rs.getString(15));
				
				reviewlist.add(review);
			}
		} catch (Exception e) {
			System.out.println("review waitlist() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return reviewlist;
	}
	public Review select(int rid) throws SQLException {
		String sql="select r.*, p.pname from review r, product p where rid=? and p.pid=r.pid";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Review review=new Review();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				review.setRid(rs.getInt(1));
				review.setSid(rs.getInt(2));
				review.setOid(rs.getInt(3));
				review.setRwriter(rs.getString(4));
				review.setRtitle(rs.getString(5));
				review.setRcontent(rs.getString(6));
				review.setRimg(rs.getString(7));
				review.setRdate(rs.getTimestamp(8));
				review.setRhit(rs.getInt(9));
				review.setRcmt(rs.getString(10));
				review.setRcmtdate(rs.getTimestamp(11));
				review.setOdate(rs.getTimestamp(12));
				review.setPid(rs.getInt(13));
				review.setPname(rs.getString(14).substring(0, 15));
			}
		} catch (Exception e) {
			System.out.println("review select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return review;
	}
	public void upHit(int rid) throws SQLException {
		String sql="update review set rhit=rhit+1 where rid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("upHit() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
	}
	public int editCMT(int rid, String rcmt) throws SQLException {
		String sql="update review set rcmt=?, rcmtdate=sysdate where rid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, rcmt);
			pstmt.setInt(2, rid);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("review EditCMT() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	public int deleteCMT(int rid) throws SQLException {
		String sql="update review set rcmt=? where rid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setInt(2, rid);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteCMT() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
}
