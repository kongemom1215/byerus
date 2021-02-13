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


public class QnaDao {
	private static QnaDao instance;
	
	private QnaDao() {
		
	}
	
	public static QnaDao getInstance() {
		if(instance==null)
			instance= new QnaDao();
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
	
	
	public int qna() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from qna"; 
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
	
	
	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from qna"; 
		int tot = 0;
		try { 
			conn  = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				tot = rs.getInt(1);
			}
			System.out.println("getTotalCnt tot->"+tot);
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
			if (rs != null) rs.close();
		}
		return tot;
	}
	
	public List<Qna> list(int startRow, int endRow) throws SQLException {
		List<Qna> list = new ArrayList<Qna>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		 String sql = "select * from (select rownum rn ,a.* from " + 
			" (select q.* , s.SNAME from qna q , SHOPPINGUSER s where q.sid = s.sid order by qid desc) a ) "+
			" where rn between ? and ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Qna qna = new Qna();
				qna.setQid(rs.getInt("qid"));
				qna.setSid(rs.getInt("sid"));
				qna.setQctg(rs.getString("qctg"));
				qna.setPid(rs.getInt("pid"));
				qna.setOid(rs.getInt("oid"));
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getDate("qdate"));
				qna.setQfile(rs.getString("qfile"));
				qna.setQcmt(rs.getString("qcmt"));
				qna.setOdate(rs.getDate("odate"));
				qna.setSname(rs.getString("sname"));
				
				list.add(qna);
			}
		} catch(Exception e) {	System.out.println(e.getMessage()); 
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		} 
		return list;
	}

	public int call(String qpwd) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select * from qna where qpwd = ?";
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			 pstmt.setString(1, qpwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				Qna qna = new Qna();
				qna.setQid(rs.getInt("qid"));
				qna.setSid(rs.getInt("sid"));
				qna.setQctg(rs.getString("qctg"));
				qna.setPid(rs.getInt("pid"));
				qna.setOid(rs.getInt("oid"));
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getDate("qdate"));
				qna.setQfile(rs.getString("qfile"));
				qna.setQcmt(rs.getString("qcmt"));
				qna.setOdate(rs.getDate("odate"));
				qna.setQpwd(rs.getInt("qpwd"));
				
				return result=1;
				
			}
			else
				return result=-1;  
			
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
		return result;
	}
	
	
	public Qna find(int sid) throws SQLException {
		String sql="select * from qna where sid = ?";
		Qna qna = new Qna();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				qna.setQid(rs.getInt("qid"));
				qna.setSid(rs.getInt("sid"));
				qna.setQctg(rs.getString("qctg"));
				qna.setPid(rs.getInt("pid"));
				qna.setOid(rs.getInt("oid"));
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getDate("qdate"));
				qna.setQfile(rs.getString("qfile"));
				qna.setQcmt(rs.getString("qcmt"));
				qna.setOdate(rs.getDate("odate"));
				qna.setQpwd(rs.getInt("qpwd"));
				
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
		
		return qna;
	}
	
	
	
	public int insert(Qna qna) throws SQLException {
		int sid = qna.getSid();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select nvl(max(qid),0) from qna";
		String sql="insert into qna values(?,?,?,?,?,?,sysdate,?,?,sysdate,sysdate)";

		try { 
			conn  = getConnection();

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			// 일단 max사용
			int number = rs.getInt(1) + 1;
			rs.close();
			pstmt.close();
			if (sid == 0 ) qna.setSid(number);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, number);
			pstmt.setInt(2, qna.getSid());
			pstmt.setString(3, qna.getQctg());
			pstmt.setInt(4, qna.getPid());
			pstmt.setInt(5, qna.getOid());
			pstmt.setString(6, qna.getQcontent());
			pstmt.setString(7, qna.getQfile());
			pstmt.setString(8, qna.getQcmt());
			
			result = pstmt.executeUpdate();
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	
	
	public int answer(Qna qna) throws SQLException {
		int qid = qna.getQid();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select nvl(max(qid),0) from qna";
		String sql="insert into qna values(?,?,?,?,?,?,sysdate,?,?,sysdate,?)";

		try { 
			conn  = getConnection();

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			// 일단 max사용
			int number = rs.getInt(1) + 1;
			rs.close();
			pstmt.close();
			if (qid == 0 ) qna.setSid(number);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, number);
			pstmt.setInt(2, qna.getSid());
			pstmt.setString(3, qna.getQctg());
			pstmt.setInt(4, qna.getPid());
			pstmt.setInt(5, qna.getOid());
			pstmt.setString(6, qna.getQcontent());
			pstmt.setString(7, qna.getQfile());
			pstmt.setString(8, qna.getQcmt());
			pstmt.setInt(9, qna.getQpwd());
			result = pstmt.executeUpdate();
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	
	  public Qna select(int qid) throws SQLException { Qna qna = new Qna();
	  Connection conn = null;
	  String sql = "select * from qna where qid="+qid;
	  PreparedStatement pstmt = null; 
	  ResultSet rs = null; 
	  try { conn = getConnection(); 
	  pstmt = conn.prepareStatement(sql); 
	  rs =pstmt.executeQuery(); 
	  if (rs.next()) { 
		  qna.setQid(rs.getInt("qid"));
	  qna.setSid(rs.getInt("sid")); 
	  qna.setQctg(rs.getString("qctg"));
	  qna.setPid(rs.getInt("pid"));
	  qna.setOid(rs.getInt("oid"));
	  qna.setQcontent(rs.getString("qcontent"));
	  qna.setQdate(rs.getDate("qdate"));
	  qna.setQfile(rs.getString("qfile"));
	  qna.setQcmt(rs.getString("qcmt"));
	  qna.setQcmtdate(rs.getDate("qcmtdate"));
	  qna.setOdate(rs.getDate("odate"));
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
	  return qna; 
	  }
	 
	
	
	public int update(Qna qna) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql="update qna set qcontent=?,qfile=? where qid=?";

		try { 
			conn  = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qna.getQcontent());
			pstmt.setString(2, qna.getQfile());
			pstmt.setInt(3, qna.getQid());
			
			result = pstmt.executeUpdate();
			System.out.println(result);
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	
	
	public int delete(int qid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql="delete from qna where qid = ?";

		try { 
			conn  = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qid);
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
