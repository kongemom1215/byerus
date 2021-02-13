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

import oracle.net.aso.f;

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
		String sql = "select * from (select rownum, a.* from (SELECT * from product a order by psell desc) a) where rownum <= 12";
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
					product.setPhit(rs.getInt("phit"));
					product.setPdiscount(rs.getInt("pdiscount"));
					product.setPpublic(rs.getInt("ppublic"));
					product.setPthumbimg(rs.getString("pthumbimg"));
					product.setPoption(rs.getString("poption"));
					list.add(product);
				} while (rs.next());
			}
		} catch (Exception e) {

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

	public List<Product> bestproducts() throws Exception {
		List<Product> bestproducts = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ( select rownum rn, a.* from (select * from product where (psell is not null and ppublic = 1 ) order by psell desc) a ) where rn between 1 and 3";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Product prdt = new Product();
					prdt.setPid(rs.getInt("pid"));
					prdt.setPname(rs.getString("pname"));
					prdt.setPprice(rs.getInt("pprice"));
					prdt.setPdiscount(rs.getInt("pdiscount"));
					prdt.setPthumbimg(rs.getString("pthumbimg"));
					bestproducts.add(prdt);
				} while (rs.next());
			}

		} catch (Exception e) {

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return bestproducts;
	}

	public int getTotalCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from product where ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				tot = rs.getInt(1);
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
		return tot;

	}

	public int getCheonCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cheonCnt = 0;
		String sql = "select count(*) from product where ptype = 'cheon' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				cheonCnt = rs.getInt(1);
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
		return cheonCnt;

	}

	public int getAlcoCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int alcoCnt = 0;
		String sql = "select count(*) from product where ptype = 'alco' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				alcoCnt = rs.getInt(1);
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
		return alcoCnt;

	}

	public int getSodokCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sodokCnt = 0;
		String sql = "select count(*) from product where ptype = 'sodok' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				sodokCnt = rs.getInt(1);
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
		return sodokCnt;

	}

	public int getMaskCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maskCnt = 0;
		String sql = "select count(*) from product where ptype = 'mask' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				maskCnt = rs.getInt(1);
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
		return maskCnt;

	}

	public int getBangdokCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int bangdokCnt = 0;
		String sql = "select count(*) from product where ptype = 'bangdok' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				bangdokCnt = rs.getInt(1);
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
		return bangdokCnt;

	}

	public int getSejungCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int sejungCnt = 0;
		String sql = "select count(*) from product where ptype = 'sejung' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				sejungCnt = rs.getInt(1);
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
		return sejungCnt;

	}

	public int getHomeCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int homeCnt = 0;
		String sql = "select count(*) from product where ptype = 'home' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				homeCnt = rs.getInt(1);
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
		return homeCnt;

	}

	public int getEtcCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int etcCnt = 0;
		String sql = "select count(*) from product where ptype = 'etc' and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				etcCnt = rs.getInt(1);
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
		return etcCnt;

	}

	public int getSaleCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int saleCnt = 0;
		String sql = "select count(*) from product where (pdiscount is not null and pdiscount != 0)  and ppublic = 1";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				saleCnt = rs.getInt(1);
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
		return saleCnt;

	}

	public List<Product> hotlist(int startRow, int endRow, String displayoption, String cate) throws Exception {
		List<Product> hotlist = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;

		if (cate == "total" || cate.equals("total")) {

			switch (displayoption) {
			case "hot":
			default:
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by psell desc) a )"
						+ " where rn between ? and ?";
				break;
			case "new":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by pregdate desc) a )"
						+ " where rn between ? and ?";
				break;

			case "expensive":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by pprice desc) a )"
						+ " where rn between ? and ?";
				break;

			case "cheap":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by pprice) a )" + " where rn between ? and ?";
				break;

			}
		} else if (cate == "sale" || cate.equals("sale")) {

			switch (displayoption) {
			case "hot":
			default:
				sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
						+ "and ppublic = 1 order by psell desc) a ) where rn between ? and ?";
				break;
			case "new":
				sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
						+ "and ppublic = 1 order by pregdate desc) a ) where rn between ? and ?";
				break;

			case "expensive":
				sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
						+ "and ppublic = 1 order by pprice desc) a ) where rn between ? and ?";
				break;

			case "cheap":
				sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
						+ "and ppublic = 1 order by pprice) a ) where rn between ? and ?";
				break;
			}

		} else {
			switch (displayoption) {
			case "hot":
			default:
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by psell desc) a where ptype = ? )"
						+ " where rn between ? and ?";
				break;
			case "new":
				sql = "select * from (select rownum rn, a.* from (select * from product where ppublic = 1 order by pregdate desc) a where ptype = ? )"
						+ " where rn between ? and ?";
				break;

			case "expensive":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by pprice desc) a where ptype = ?)"
						+ " where rn between ? and ?";
				break;

			case "cheap":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product where ppublic = 1 order by pprice) a where ptype = ?)"
						+ " where rn between ? and ?";
				break;

			}
		}

		System.out.println("hotlist sql -> " + sql);

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			if (cate == "total" || cate.equals("total") || cate == "sale" || cate.equals("sale")) {

				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();

			} else {
				pstmt.setString(1, cate);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();

			}

			while (rs.next()) {
				Product product = new Product();
				product.setPname(rs.getString("pname"));
				product.setPprice(rs.getInt("pprice"));
				product.setPdiscount(rs.getInt("pdiscount"));
				product.setPinventory(rs.getInt("pinventory"));
				product.setPregdate(rs.getDate("pregdate"));
				product.setPthumbimg(rs.getString("pthumbimg"));
				product.setPid(rs.getInt("pid"));
				hotlist.add(product);
			}
		} catch (Exception exc) {
			System.out.println(exc.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return hotlist;

	}

	public Product getDetailInfo(int pid) throws Exception {
		Product prdt = new Product();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from product where pid = ?";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs = pstmt.executeQuery();

			if (rs.next())
				prdt.setPid(pid);
			prdt.setPname(rs.getString("pname"));
			prdt.setPprice(rs.getInt("pprice"));
			prdt.setPdiscount(rs.getInt("pdiscount"));
			prdt.setPinventory(rs.getInt("pinventory"));
			prdt.setPregdate(rs.getDate("pregdate"));
			prdt.setPthumbimg(rs.getString("pthumbimg"));
			prdt.setPtype(rs.getString("ptype"));
			prdt.setPoption(rs.getString("poption"));

			if (rs.getString("col1") == null || rs.getString("col1").equals("null")) {
				prdt.setCol1("");
			} else {
				prdt.setCol1(rs.getString("col1"));
			}

			if (rs.getString("col2") == null || rs.getString("col2").equals("null")) {
				prdt.setCol2("");
			} else {
				prdt.setCol2(rs.getString("col2"));
			}

			if (rs.getString("col3") == null || rs.getString("col3").equals("null")) {
				prdt.setCol3("");
			} else {
				prdt.setCol3(rs.getString("col3"));
			}

		} catch (Exception e) {
			System.out.println("getdetailinfo error" + e.getMessage());

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return prdt;
	}

	public List<Product> getbest4products(String cate) throws Exception {

		List<Product> best4products = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ( select rownum rn, a.* from (select * from product where (psell is not null and ppublic = 1 and ptype = ? ) "
				+ "order by psell desc) a ) where rn between 1 and 4";

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					Product prdt = new Product();
					prdt.setPid(rs.getInt("pid"));
					prdt.setPname(rs.getString("pname"));
					prdt.setPprice(rs.getInt("pprice"));
					prdt.setPdiscount(rs.getInt("pdiscount"));
					prdt.setPthumbimg(rs.getString("pthumbimg"));
					best4products.add(prdt);
				} while (rs.next());
			}

		} catch (Exception e) {

		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return best4products;
	}

}
