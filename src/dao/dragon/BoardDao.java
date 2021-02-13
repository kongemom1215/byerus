package dao.dragon;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.heart.ShoppingUser;


public class BoardDao {
	
	private static BoardDao instance;
	private BoardDao() {}
	public static BoardDao getInstance() {
		if (instance == null) {	
			instance = new BoardDao();		
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
			System.out.println("오류 : "+ e.getMessage());
			}
		return conn;
	}
	
	public List<Board> notice_list() throws SQLException {
		System.out.println("-- dao.dragon.BoardDao.notice_list --");
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice";
//		System.out.println("sql 문 : " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Board board = new Board();
					board.setNid(rs.getInt("nid"));
					board.setNtitle(rs.getNString("ntitle"));
					board.setNcontent(rs.getString("ncontent"));
					board.setNpublic(rs.getInt("npublic"));
					board.setNdate(rs.getDate("ndate"));
					board.setNfile(rs.getString("nfile"));
					board.setNhit(rs.getInt("nhit"));
					list.add(board);
				} while (rs.next());
			}
			
		} catch (Exception e) {
			System.out.println("오류 : "+ e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		return list;
	}
	
	public void notice_nhit(int nid) throws SQLException {
		System.out.println("-- dao.dragon.BoardDao.notice_nhit --");
		Connection conn = null;	
		PreparedStatement pstmt= null;
		int rs;
		String sql = "UPDATE notice SET nhit = nhit + 1 WHERE nid = ?";
//		System.out.println("sql 문 : " + sql);
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("오류 : "+ e.getMessage());
		} finally {
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
	}
	
	public Board select_qna(int qid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from qna where qid= ?";
		Board board = new Board();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setQid(rs.getInt("qid"));
				board.setQctg(rs.getString("qctg"));
				board.setPid(rs.getInt("pid"));
				board.setOid(rs.getInt("oid"));
				board.setQcontent(rs.getString("qcontent"));
				board.setQdate(rs.getDate("qdate"));
				board.setQfile(rs.getString("qfile"));
				board.setQcmt(rs.getString("qcmt"));
				board.setQcmtdate(rs.getDate("qcmtdate")); 
				board.setOdate(rs.getDate("odate"));
			}
		} catch (Exception e) {
			System.out.println("ShoppingUser select error=>"+e.getMessage());
		} finally {
			if (rs !=null)    rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null)  conn.close();
		}
		
		return board;
	}
	
	public Board select_reivew(int rid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String sql = "select * from review where rid= ?";
		Board board = new Board();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setRid(rs.getInt("rid"));
				board.setSid(rs.getInt("sid"));
				board.setOid(rs.getInt("oid"));
				board.setPid(rs.getInt("pid"));
				board.setRwriter(rs.getString("rwriter"));
				board.setRtitle(rs.getString("rtitle"));
				board.setRcontent(rs.getString("rcontent"));
				board.setRimg(rs.getString("rimg"));
				board.setRdate(rs.getDate("rdate"));
				board.setRhit(rs.getInt("rhit"));
				board.setRcmt(rs.getString("rcmt"));
				board.setRcmtdate(rs.getDate("rcmtdate"));
				board.setOdate(rs.getDate("odate"));
			}
		} catch (Exception e) {
			System.out.println("ShoppingUser select error=>"+e.getMessage());
		} finally {
			if (rs !=null)    rs.close(); 	
			if (pstmt != null) pstmt.close();
			if (conn !=null)  conn.close();
		}
		
		return board;
	}
	
	public List<Board> qna_list() throws SQLException {
		System.out.println("-- dao.dragon.BoardDao.qna_list --");
		List<Board> list = new ArrayList<Board>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "SELECT * FROM notice";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Board board = new Board();
					board.setQid(rs.getInt("qid"));
					board.setSid(rs.getInt("sid"));
					int sid = rs.getInt("sid");
					board.setQctg(rs.getString("qctg"));
					board.setPid(rs.getInt("pid"));
					board.setOid(rs.getInt("oid"));
					board.setQcontent(rs.getString("qcontent"));
					board.setQdate(rs.getDate("qdate"));
					board.setQfile(rs.getString("qfile"));
					board.setQcmt(rs.getString("qcmt"));
					board.setQcmtdate(rs.getDate("qcmtdate"));
					board.setOdate(rs.getDate("odate"));
					board.setQtitle(rs.getString("qtitle"));
					list.add(board);
					
					rs.close();
					
					sql = "select sname from shoppinguser where sid = ?";
					rs = pstmt.executeQuery();
					pstmt.setInt(1, sid);
					
					ShoppingUser shoppinguser = new ShoppingUser();
					shoppinguser.setSname(rs.getString("sname"));
					
					list.add(board);
				} while (rs.next());
			}
			
		} catch (Exception e) {
			System.out.println("오류 : "+ e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		return list;
	}
	

}
