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

public class NoticeDao {
	private static NoticeDao instance;
	private NoticeDao() {}
	public static NoticeDao getInstance() {
		if (instance == null) {	
			instance = new NoticeDao();		
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
	
	public int totalNotice() throws SQLException {
		int totalNotice=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from notice";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())
				totalNotice=rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println("totalNotice() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return totalNotice;
	}
	public List<Notice> list(int startRow, int endRow) throws SQLException {
		List<Notice> noticelist=new ArrayList<Notice>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn, n.* from (select * from notice order by nid desc) n) where rn between ? and ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice=new Notice();
				notice.setNid(rs.getInt(2));
				notice.setNtitle(rs.getString(3));
				notice.setNcontent(rs.getString(4));
				notice.setNpublic(rs.getInt(5));
				notice.setNdate(rs.getDate(6));
				notice.setNfile(rs.getString(7));
				notice.setNhit(rs.getInt(8));
				
				noticelist.add(notice);
			}
		} catch (Exception e) {
			System.out.println("notice list()->"+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return noticelist;
	}
	public Notice select(int nid) throws SQLException {
		Notice notice=new Notice();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from notice where nid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				notice.setNid(nid);
				notice.setNtitle(rs.getString(2));
				notice.setNcontent(rs.getString(3));
				notice.setNpublic(rs.getInt(4));
				notice.setNdate(rs.getDate(5));
				notice.setNfile(rs.getString(6));
				notice.setNhit(rs.getInt(7));
			}
		} catch (Exception e) {
			System.out.println("notice select()-> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return notice;
	}
	public String getTitle(int i) throws SQLException {
		String ntitle=null;
		String sql="select ntitle from notice where nid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, i);
			rs=pstmt.executeQuery();
			
			if(rs.next())
				ntitle=rs.getString(1);
		} catch (Exception e) {
			System.out.println("notice getTitle -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return ntitle;
	}
	
	public int delete(int nid) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from notice where nid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("notice delete() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	public int updateNotice(Notice notice) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update Notice set ntitle=?, ncontent=?, npublic=?, ndate=sysdate, nfile=? where nid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNtitle());
			pstmt.setString(2, notice.getNcontent());
			pstmt.setInt(3, notice.getNpublic());
			pstmt.setString(4, notice.getNfile());
			pstmt.setInt(5, notice.getNid());
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateNotice() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	public int insert(Notice notice) throws SQLException {
		int result=0;
		int nid=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql1="insert into notice values(?,?,?,?,sysdate,?,0)";
		String sql2="select nvl(max(nid)+1,0) from notice";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			if(rs.next())
				nid=rs.getInt(1);
			rs.close();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, nid);
			pstmt.setString(2, notice.getNtitle());
			pstmt.setString(3, notice.getNcontent());
			pstmt.setInt(4, notice.getNpublic());
			pstmt.setString(5, notice.getNfile());
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("notice insert() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
}
