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


public class QnaDao {
	private static QnaDao instance;
	private QnaDao() {}
	public static QnaDao getInstance() {
		if (instance == null) {	
			instance = new QnaDao();		
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
	public int totalQna() throws SQLException {
		int total=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from qna";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				total=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("totalQna() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return total;
	}
	public List<Qna> list(int startRow, int endRow) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn ,a.* from (select q.* , s.SNAME from qna q , SHOPPINGUSER s where q.sid = s.sid order by qid desc) a ) where rn between ? and ?";
		List<Qna> qnalist=new ArrayList<Qna>();
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Qna qna=new Qna();
				qna.setQid(rs.getInt(2));
				qna.setSid(rs.getInt(3));
				qna.setQctg(rs.getString(4));
				qna.setPid(rs.getInt(5));
				qna.setOid(rs.getInt(6));
				qna.setQcontent(rs.getString(7));
				qna.setQdate(rs.getTimestamp(8));
				qna.setQfile(rs.getString(9));
				qna.setQcmt(rs.getString(10));
				qna.setQcmtdate(rs.getTimestamp(11));
				qna.setOdate(rs.getTimestamp(12));
				qna.setSname(rs.getString(13));
				
				qnalist.add(qna);
			}
		} catch (Exception e) {
			System.out.println("qna list() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return qnalist;
	}
	public List<Qna> waitlist(int startRow, int endRow) throws SQLException {
		List<Qna> waitlist=new ArrayList<Qna>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn ,a.* from (select q.* , s.SNAME from qna q , SHOPPINGUSER s where q.sid = s.sid and qcmt is null order by qid desc) a ) where rn between ? and ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Qna qna=new Qna();
				qna.setQid(rs.getInt(2));
				qna.setSid(rs.getInt(3));
				qna.setQctg(rs.getString(4));
				qna.setPid(rs.getInt(5));
				qna.setOid(rs.getInt(6));
				qna.setQcontent(rs.getString(7));
				qna.setQdate(rs.getTimestamp(8));
				qna.setQfile(rs.getString(9));
				qna.setQcmt(rs.getString(10));
				qna.setQcmtdate(rs.getTimestamp(11));
				qna.setOdate(rs.getTimestamp(12));
				qna.setSname(rs.getString(13));
				
				waitlist.add(qna);
			}
		} catch (Exception e) {
			System.out.println("qna list() -> "+e.getMessage());
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
	public int totalWait() throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int totalWait=0;
		String sql="select count(*) from qna where qcmt is null";
		
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
	public int totalCTG(String qctg) throws SQLException {
		int totalCTG=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from qna where qctg=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, qctg);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCTG=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("totalCTG() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return totalCTG;
	}
	public List<Qna> ctglist(int startRow, int endRow, String qctg) throws SQLException {
		String sql="select * from (select rownum rn ,a.* from (select q.* , s.SNAME from qna q , SHOPPINGUSER s where q.sid = s.sid and qctg=? order by qid desc) a ) where rn between ? and ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Qna> ctglist=new ArrayList<Qna>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, qctg);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Qna qna=new Qna();
				qna.setQid(rs.getInt(2));
				qna.setSid(rs.getInt(3));
				qna.setQctg(rs.getString(4));
				qna.setPid(rs.getInt(5));
				qna.setOid(rs.getInt(6));
				qna.setQcontent(rs.getString(7));
				qna.setQdate(rs.getTimestamp(8));
				qna.setQfile(rs.getString(9));
				qna.setQcmt(rs.getString(10));
				qna.setQcmtdate(rs.getTimestamp(11));
				qna.setOdate(rs.getTimestamp(12));
				qna.setSname(rs.getString(13));
				
				ctglist.add(qna);
			}
		} catch (Exception e) {
			System.out.println("qna list() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return ctglist;
	}
	public Qna select(int qid) throws SQLException {
		Qna qna=new Qna();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select q.* , s.sname, p.pname from qna q, shoppinguser s, product p where q.sid=s.sid and q.qid=? and q.pid=p.pid";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, qid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				qna.setQid(qid);
				qna.setSid(rs.getInt(2));
				qna.setQctg(rs.getString(3));
				qna.setPid(rs.getInt(4));
				qna.setOid(rs.getInt(5));
				qna.setQcontent(rs.getString(6));
				qna.setQdate(rs.getTimestamp(7));
				qna.setQfile(rs.getString(8));
				qna.setQcmt(rs.getString(9));
				qna.setQcmtdate(rs.getTimestamp(10));
				qna.setOdate(rs.getTimestamp(11));
				qna.setSname(rs.getString(12));
				qna.setPname(rs.getString(13));
			}
		} catch (Exception e) {
			System.out.println("qna select() -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return qna;
	}
	public int deleteCMT(int qid) throws SQLException {
		String sql="update qna set qcmt=? where qid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, null);
			pstmt.setInt(2, qid);
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
	public int editCMT(int qid, String qcmt) throws SQLException {
		String sql="update qna set qcmt=?, qcmtdate=sysdate where qid=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, qcmt);
			pstmt.setInt(2, qid);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("EditCMT() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	
}

