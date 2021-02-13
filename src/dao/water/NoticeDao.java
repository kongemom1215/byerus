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

public class NoticeDao {
		private static NoticeDao instance;
			
			private NoticeDao() {
				
			}
			
			public static NoticeDao getInstance() {
				if(instance==null)
					instance= new NoticeDao();
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
				String sql = "select count(*) from notice"; 
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
			
			public List<Notice> list(int startRow, int endRow) throws SQLException {
				List<Notice> list = new ArrayList<Notice>();
				Connection conn = null;	
				PreparedStatement pstmt= null;
				ResultSet rs = null;
				 String sql = "select * from (select rownum rn ,a.* from (select n.* from notice n order by nid desc) a ) where rn between ? and ?";
				try {
					conn = getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					rs = pstmt.executeQuery();
					while (rs.next()) {
						Notice notice = new Notice();
						notice.setNid(rs.getInt("nid"));
						notice.setNtitle(rs.getNString("ntitle"));
						notice.setNcontent(rs.getString("ncontent"));
						notice.setNpublic(rs.getInt("npublic"));
						notice.setNdate(rs.getDate("ndate"));
						notice.setNfile(rs.getString("nfile"));
						notice.setNhit(rs.getInt("nhit"));
						list.add(notice);
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

			
			
			  public Review select(int sid) throws SQLException {
				  Review review = new Review();
			  Connection conn = null;
			  String sql = "select * from review where sid="+sid;
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
					String sql="update review set rcontent=? ,rtitle=? where sid=?";

					try { 
						conn  = getConnection();
						
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, review.getRcontent());
						pstmt.setString(2, review.getRtitle());
						pstmt.setInt(3, review.getSid());
						
						result = pstmt.executeUpdate();
						System.out.println(result);
					} catch(Exception e) { System.out.println(e.getMessage());
					} finally {
						if (pstmt != null) pstmt.close();
						if (conn != null) conn.close();
					}
					return result;
				}
				
				
				
				public void readCount (int nid) throws SQLException {
					Connection conn = null;	
					PreparedStatement pstmt= null;
					 String sql = "update notice set nhit=nhit+1 where nid=?"; 
					 try {
						 conn = getConnection();
						 pstmt = conn.prepareStatement(sql);
						 pstmt.setInt(1, nid);
						 pstmt.executeUpdate();
					 }catch (Exception e) {
						 System.out.println(e.getMessage());
					 }finally {
						 if(pstmt != null) pstmt.close();
						 if(conn!=null) conn.close();
					 }
				}
}
