package dao.half;

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
		if (instance == null) {
			instance = new QnaDao();
		}
		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public String getWritername(int sid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sname = null;
		String sql = "select rownum, a.* from (select q.sid, s.sname from qna q, shoppinguser s where q.sid = s.sid ) a where rownum <= 1 and sid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				sname = rs.getString(3);
			}
		} catch (Exception e) {
			System.out.println("qna sname 가져오기 에러 : " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return sname;

	}

	public List<Qna> qlist(int startRow, int endRow, int pid) throws SQLException {
		List<Qna> qlist = new ArrayList<Qna>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.println("startrow : " + startRow);
		System.out.println("endrow : " + endRow);
		System.out.println("pid : " + pid);

		String sql = "select * from (select rownum rn ,a.* from (select q.*, s.sname from qna q, shoppinguser s where q.pid = ? and q.sid = s.sid order by qdate desc) a ) where rn between ? and ?";
		System.out.println("--------------------------------------------------------------");
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			/*
			 * if (rs.next()) { System.out.println("o"); } else { System.out.println("x"); }
			 */
			
			while (rs.next()) {
				System.out.println("rs가 있는가?");
				Qna qna = new Qna();
				qna.setQid(rs.getInt("qid"));
				qna.setSid(rs.getInt("sid"));
				qna.setQctg(rs.getString("qctg"));
				qna.setPid(pid);
				qna.setQcontent(rs.getString("qcontent"));
				qna.setQdate(rs.getDate("qdate"));
				qna.setQfile(rs.getString("qfile"));
				qna.setQcmt(rs.getString("qcmt"));
				qna.setSname(rs.getString("sname"));
				System.out.println("문의남긴회원번호 : " + rs.getInt("sid"));
				System.out.println("문의남긴회원이름 : " + rs.getString("sname"));

				qlist.add(qna);
			}

		} catch (Exception e) {
			System.out.println("QnaDao qlist error :" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return qlist;

	}

	public int getTotalCnt(int pid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from qna where pid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if (rs.next())
				tot = rs.getInt(1);
			System.out.println("qna tot 카운트 값 : " + tot);
		} catch (Exception e) {
			System.out.println("qna tot 가져오기 에러 : " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		return tot;

	}

}