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

public class OrderDao {
	private static OrderDao instance;

	private OrderDao() {
	}

	public static OrderDao getInstance() {
		if (instance == null) {
			instance = new OrderDao();
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

	public int buycheck(int sid, int pid) throws Exception {
		int result = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sid, pid from (select * from order_tb, orderdetail where order_tb.oid = orderdetail.oid) where sid = ? and pid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, pid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 0;
			} else {
				result = -1;
			}
			// 0이면 구매이력있음, -1이면 구매이력없음

		} catch (Exception e) {
			System.out.println("half orderdao buycheck error : " + e.getMessage());
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

	public int writecheck(int sid, int pid) throws SQLException {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select rid, rtitle from review where sid = ? and pid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, pid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 7;
			} else {
				result = 0;
			}
			// 0이면 구매이력있고 글 안씀, 7이면 구매이력있고 글쓴 적 있음 

		} catch (Exception e) {
			System.out.println("half orderdao writecheck error : " + e.getMessage());
			result = -7;
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

}
