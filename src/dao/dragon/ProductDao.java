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

public class ProductDao {
	private static ProductDao instance;

	private ProductDao() {
	}

	public static ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
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

	public List<Product> main_img() throws SQLException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum, a.* from (SELECT * from product a order by psell desc) a) where rownum <= 12 and ppublic = 1";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Product product = new Product();
					product.setPid(rs.getInt("pid"));
					product.setPtype(rs.getString("ptype"));
					product.setCol1(rs.getString("col1"));
					product.setCol2(rs.getString("col2"));
					product.setCol3(rs.getString("col3"));
					product.setPprice(rs.getInt("pprice"));
					product.setPname(rs.getString("pname"));
					product.setPregdate(rs.getDate("pregdate"));
					product.setPsell(rs.getInt("psell"));
					product.setPdiscount(rs.getInt("pdiscount"));
					product.setPpublic(rs.getInt("ppublic"));
					product.setPthumbimg(rs.getString("pthumbimg"));
					product.setPoption(rs.getString("poption"));
					list.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return list;

	}

	public int jjim(int sid, int pid) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs_same = null;
		int same = 0;
		int rs = 0;
		String sql_same = "select * from cartnwish where cwtype='wish' and sid=? and pid=?";
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_same);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, pid);
			rs_same = pstmt.executeQuery();

			if (rs_same.next()) {

				same += 1;

			} else {
				
				String sql = "insert into cartnwish (cwid, sid, pid, cwtype) SELECT max(cwid) + 1, ? ,?, 'wish' from cartnwish";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sid);
				pstmt.setInt(2, pid);
				rs = pstmt.executeUpdate();
				if (rs >= 1) {
					same += 2;
				}
			}

			return same;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs_same != null)
				rs_same.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
			return same;
	}
	
	public List<Product> jjimForm(int sid) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT p.pname, p.pprice, p.pid, p.pthumbimg from cartnwish c, product p where c.cwtype='wish' and c.sid = ? and c.pid = p.pid";
		List<Product> list = new ArrayList<Product>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				do {
					Product product = new Product();
					product.setPname(rs.getString("pname"));
					product.setPprice(rs.getInt("pprice"));
					product.setPid(rs.getInt("pid"));
					product.setPthumbimg(rs.getString("pthumbimg"));
					list.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
		return list;
	}
	
	public void jjimdel(int sid, int pid) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rs = 0;
		String sql = "DELETE from cartnwish where sid = ? and pid = ? and cwtype = 'wish'";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, pid);
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
	}

}
