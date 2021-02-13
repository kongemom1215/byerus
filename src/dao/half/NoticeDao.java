package dao.half;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeDao {

	private static NoticeDao instance;

	private NoticeDao() {
	}

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
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public Notice getlatest() throws Exception {
		Notice ntc = new Notice();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum rn, a.*  from (select * from notice order by nid desc) a) where rn = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				ntc.setNtitle(rs.getString("ntitle"));
				ntc.setNcontent(rs.getString("ncontent"));
				ntc.setNfile(rs.getString("nfile"));
			} else {
				ntc.setNtitle(rs.getString("nonotice"));
				ntc.setNcontent(rs.getString("nonotice"));
				ntc.setNfile(rs.getString("nonotice"));
			}

		} catch (Exception e) {
			System.out.println("getlatest error : " + e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return ntc;
	}

}
