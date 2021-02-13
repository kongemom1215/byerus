package dao.water;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDao {
private static ReviewDao instance;
	
	private ReviewDao() {
		
	}
	
	public static ReviewDao getInstance() {
		if(instance==null)
			instance= new ReviewDao();
		return instance;
	}
	private Connection getConnection() {
		Connection conn=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return conn;
	}
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from review"; 
		int tot = 0;
		try { 
			conn  = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tot = rs.getInt(1);
			}
			System.out.println("tot->"+tot);
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			if (rs != null) rs.close();
		}
		return tot;
	}
	
	
	
	
	
	public List<Review> list(int startRow, int endRow) throws SQLException {
		List<Review> list = new ArrayList<Review>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		 String sql = "select * from (select rownum rn ,a.* from " + 
			" (select r.* , s.SNAME from review r , SHOPPINGUSER s where r.sid = s.sid order by rid desc) a ) "+
			" where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Review review = new Review();
				review.setRid(rs.getInt("rid"));
				review.setSid(rs.getInt("sid"));
				review.setOid(rs.getInt("oid"));
				review.setPid(rs.getInt("pid"));
				review.setRwriter(rs.getString("rwriter"));
				review.setRtitle(rs.getString("rtitle"));
				review.setRcontent(rs.getString("rcontent"));
				review.setRimg(rs.getString("rimg"));
				review.setRdate(rs.getDate("rdate"));
				review.setRhit(rs.getInt("rhit"));
				review.setRcmt(rs.getString("rcmt"));
				review.setRcmtdate(rs.getDate("rcmtdate"));
				review.setOdate(rs.getDate("odate"));
				review.setSname(rs.getString("sname"));
				
				list.add(review);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 
		return list;
	}
	
	
	public Review find(int rid) throws SQLException {
		String sql="select * from review where rid = ?";
		Review review = new Review();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				review.setRid(rs.getInt("rid"));
				review.setSid(rs.getInt("sid"));
				review.setOid(rs.getInt("oid"));
				review.setPid(rs.getInt("pid"));
				review.setRwriter(rs.getString("rwriter"));
				review.setRtitle(rs.getString("rtitle"));
				review.setRcontent(rs.getString("rcontent"));
				review.setRimg(rs.getString("rimg"));
				review.setRdate(rs.getDate("rdate"));
				review.setRhit(rs.getInt("rhit"));
				review.setRcmt(rs.getString("rcmt"));
				review.setRcmtdate(rs.getDate("rcmtdate"));
				review.setOdate(rs.getDate("odate"));
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	
	public int insert(Review review) throws SQLException {
		int sid = review.getSid();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select nvl(max(rid),0) from review";
		String sql="insert into review values(?,?,?,?,?,?,?,sysdate,?,?,null,sysdate,?)";

		try { 
			conn  = getConnection();

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			// 일단 max사용
			int number = rs.getInt(1) + 1;
			rs.close();
			pstmt.close();
			if (sid == 0 ) review.setSid(number);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, number);
			pstmt.setInt(2, review.getSid());
			pstmt.setInt(3, review.getOid());
			pstmt.setString(4, review.getRwriter());
			pstmt.setString(5, review.getRtitle());
			pstmt.setString(6, review.getRcontent());
			pstmt.setString(7, review.getRimg());
			pstmt.setInt(8, review.getRhit());
			pstmt.setString(9, review.getRcmt());
			pstmt.setInt(10, review.getPid());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	
	  public Review select(int rid) throws SQLException {
		  Review review = new Review();
	  Connection conn = null;
	  String sql = "select * from review where rid="+rid;
	  PreparedStatement pstmt = null; 
	  ResultSet rs = null; 
	  try { conn = getConnection(); 
	  pstmt = conn.prepareStatement(sql); 
	  rs =pstmt.executeQuery(); 
	  if (rs.next()) { 
		  review.setRid(rs.getInt("rid"));
			review.setSid(rs.getInt("sid"));
			review.setOid(rs.getInt("oid"));
			review.setPid(rs.getInt("pid"));
			review.setRwriter(rs.getString("rwriter"));
			review.setRtitle(rs.getString("rtitle"));
			review.setRcontent(rs.getString("rcontent"));
			review.setRimg(rs.getString("rimg"));
			review.setRdate(rs.getDate("rdate"));
			review.setRhit(rs.getInt("rhit"));
			review.setRcmt(rs.getString("rcmt"));
			review.setRcmtdate(rs.getDate("rcmtdate"));
			review.setOdate(rs.getDate("odate"));
			} 
	  }catch(Exception e) { 
		  System.out.println(e.getMessage()); }
	  finally { if(pstmt != null) 
		  pstmt.close(); 
	  			if (conn != null) 
	  	  conn.close(); 
	  			if (rs !=null)
	  	  rs.close(); 
	  			}
	  return review; 
	  }
	 
	
		public int update(Review review) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			String sql="update review set rcontent=? ,rtitle=?,rimg=? where rid=?";

			try { 
				conn  = getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, review.getRcontent());
				pstmt.setString(2, review.getRtitle());
				pstmt.setString(3, review.getRimg());
				pstmt.setInt(4, review.getRid());
				
				result = pstmt.executeUpdate();
				System.out.println(result);
			} catch(Exception e) { System.out.println(e.getMessage());
			} finally {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			return result;
		}
		
		
		
		public void readCount (int rid) throws SQLException {
			Connection conn = null;	
			PreparedStatement pstmt= null;
			 String sql = "update review set rhit=rhit+1 where rid=?"; 
			 try {
				 conn = getConnection();
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, rid);
				 pstmt.executeUpdate();
			 }catch (Exception e) {
				 System.out.println(e.getMessage());
			 }finally {
				 if(pstmt != null) pstmt.close();
				 if(conn!=null) conn.close();
			 }
		 }
		
		
		public int delete(int rid) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			ResultSet rs = null;
			String sql="delete from review where rid = ?";

			try { 
				conn  = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rid);
				rs = pstmt.executeQuery();
				result = pstmt.executeUpdate();
			} catch(Exception e) {
				System.out.println("삭제 dao오류="+e.getMessage());
			} finally {
				if (pstmt != null)  pstmt.close();
				if (conn != null)   conn.close();
				if (rs != null) 	rs.close();
			}
			return result;
		}
		
}
