package dao.half;

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

	private ReviewDao() {
	}

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
		String sql = "select sname from (select r.sid, s.sname from review r, shoppinguser s where r.sid = s.sid )"
				+ "where sid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			if (rs.next())
			sname = rs.getString(1);
		} catch (Exception e) {
			System.out.println("review sname 가져오기 에러 : " + e.getMessage());
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
	
	
	
	public List<Review> rlist(int startRow, int endRow, int pid) throws SQLException {
		List<Review> rlist = new ArrayList<Review>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sname = null;

		String sql = "select * from (select rownum rn ,a.* from "
				+ " (select * from review where pid = ? order by rdate desc) a ) " + " where rn between ? and ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Review review = new Review();
				review.setRid(rs.getInt("rid"));
				review.setSid(rs.getInt("sid"));
				review.setSname(getWritername(rs.getInt("sid")));
				review.setPid(pid);
				review.setRtitle(rs.getString("rtitle"));
				review.setRcontent(rs.getString("rcontent"));
				review.setRdate(rs.getDate("rdate"));
				rlist.add(review);
			}

		} catch (Exception e) {
			System.out.println("ReviewDao rlist error :" + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return rlist;

	}

	public int getTotalCnt(int pid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from review where pid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();
			if (rs.next())
				tot = rs.getInt(1);
			System.out.println("review tot 카운트 값 : " + tot);
		} catch (Exception e) {
			System.out.println("review tot 가져오기 에러 : " + e.getMessage());
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
