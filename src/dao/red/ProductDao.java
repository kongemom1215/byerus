package dao.red;

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

	public int productAdd(String pname, String ptype, int pprice, int pinventory, String poption) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql_max = "SELECT max(pid) pid FROM product";
		int pid_max = 0;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql_max);
			rs = pstmt.executeQuery();
			rs.next();
			pid_max = rs.getInt("pid");
			System.out.println(pid_max + 1);
			String sql = "insert into PRODUCT (pid, pname, ptype, pprice, pinventory, poption) values (?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid_max + 1);
			pstmt.setString(2, pname);
			pstmt.setString(3, ptype);
			pstmt.setInt(4, pprice);
			pstmt.setInt(5, pinventory);
			pstmt.setString(6, poption);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public List<Product> main_img() throws SQLException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from (select rownum, a.* from (SELECT * from product a order by psell desc) a) where rownum <= 6";
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
					product.setCol4(rs.getString("col4"));
					product.setCol5(rs.getString("col5"));
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
		String sql = "select * from ( select rownum rn, a.* from (select * from product where psell is not null order by psell desc) a ) where rn between 1 and 3";

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

	public List<Product> defaultdisplay() throws Exception {
		List<Product> defaultproducts = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pid, pname, pprice, pdiscount from ( select rownum rn, a.pid, a.pname, a.pprice, a.pdiscount from (select * from product where psell is not null order by psell desc) a ) where rn between 1 and 12";

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
					defaultproducts.add(prdt);
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

		return defaultproducts;
	}

	public int getTotalCnt() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		String sql = "select count(*) from product";

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
		String sql = "select count(*) from product where ptype = 'cheon'";

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
		String sql = "select count(*) from product where ptype = 'alco'";

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
		String sql = "select count(*) from product where ptype = 'sodok'";

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
		String sql = "select count(*) from product where ptype = 'mask'";

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
		String sql = "select count(*) from product where ptype = 'bangdok'";

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
		String sql = "select count(*) from product where ptype = 'sejung'";

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
		String sql = "select count(*) from product where ptype = 'home'";

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
		String sql = "select count(*) from product where ptype = 'etc'";

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
		String sql = "select count(*) from product where (pdiscount is not null or pdiscount != 0)";

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
				sql = "select * from (select rownum rn, a.* from " + "(select * from product order by psell desc) a )"
						+ " where rn between ? and ?";
				break;
			case "new":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product order by pregdate desc) a )" + " where rn between ? and ?";
				break;

			case "expensive":
				sql = "select * from (select rownum rn, a.* from " + "(select * from product order by pprice desc) a )"
						+ " where rn between ? and ?";
				break;

			case "cheap":
				sql = "select * from (select rownum rn, a.* from " + "(select * from product order by pprice) a )"
						+ " where rn between ? and ?";
				break;

			}
		} else if (cate == "sale" || cate.equals("sale")) {
				
				switch (displayoption) {
				case "hot":
				default:
					sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
							+ "order by psell desc) a ) where rn between ? and ?";
					break;
				case "new":
					sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
							+ "order by pregdate desc) a ) where rn between ? and ?";
					break;

				case "expensive":
					sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
							+ "order by pprice desc) a ) where rn between ? and ?";
					break;

				case "cheap":
					sql = "select * from (select rownum rn, a.* from (select * from product where (pdiscount is not null and pdiscount != 0) "
							+ "order by pprice) a ) where rn between ? and ?";
					break;
				}

		} else {
			switch (displayoption) {
			case "hot":
			default:
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product order by psell desc) a where ptype = ? )"
						+ " where rn between ? and ?";
				break;
			case "new":
				sql = "select * from (select rownum rn, a.* from (select * from product order by pregdate desc) a where ptype = ? )"
						+ " where rn between ? and ?";
				break;

			case "expensive":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product order by pprice desc) a where ptype = ?)"
						+ " where rn between ? and ?";
				break;

			case "cheap":
				sql = "select * from (select rownum rn, a.* from "
						+ "(select * from product order by pprice) a where ptype = ?)" + " where rn between ? and ?";
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
				prdt.setCol1(rs.getString("col1"));
				prdt.setCol2(rs.getString("col2"));
				prdt.setCol3(rs.getString("col3"));
			
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
//홍주가 필요한 메소드 나중에 지우면 됨 나머지는 반야님꺼
public List<Product> select() throws SQLException {
		
		List<Product> list=new ArrayList<Product>();
		Connection conn = null;
		Statement stmt= null;
		ResultSet rs = null;
		String sql="select pid,pname,pprice from product ";
		try {
			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			if(rs.next()) {
			 do{
				 
				Product product = new Product();
				product.setPid(rs.getInt(1));
				product.setPname(rs.getString(2));
				product.setPprice(rs.getInt(3));
				/*
				 * product.setPtype(rs.getString(2)); product.setPexplain(rs.getString(3));
				 * product.setCol(rs.getString(4)); product.setCol2(rs.getString(5));
				 * product.setCol3(rs.getString(6)); product.setCol4(rs.getString(7));
				 * product.setPprice(rs.getInt(8)); product.setPinventory(rs.getShort(9));
				 * product.setPname(rs.getString(10)); product.setPregdate(rs.getDate(11));
				 * product.setPsell(rs.getInt(12)); product.setPhit(rs.getInt(13));
				 * product.setPdiscount(rs.getInt(14)); product.setPpublic(rs.getInt(15));
				 * product.setPthumbing(rs.getString(16)); product.setPoption(rs.getString(17));
				 */
				list.add(product);
				
			}while(rs.next());
		}
			
		} catch (Exception e) {
			System.out.println("ProductDao 에러"+e.getMessage());
		}finally{
			if(conn != null) conn.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		}

		
		return list;
	}

//홍주 goOrderAction
	public Product select(int pid) throws SQLException {
		Product product = new Product();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pprice,pdiscount,pname from product where pid=?";
		
		try {
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				product.setPprice(rs.getInt("pprice"));
				product.setPdiscount(rs.getInt("pdiscount"));
				product.setPname(rs.getString("pname"));
			}
			
			
		}catch(Exception e) {
			System.out.println("ProductDao select error!!==>"+e.getMessage());
		}finally{
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}

		
		return product;
	}

//주문완료 후 재고관리
 public int reduce(int pid) throws SQLException {
	 int reduce=0;
	 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update product set psell=psell+1 , PINVENTORY=PINVENTORY-1 where pid=?";
		
		
		try {
			
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			reduce=pstmt.executeUpdate();
			
			System.out.println("주문완료 후 재고관리 reduce==>"+reduce);	
			
		}catch(Exception e) {
			System.out.println("ProductDao reduce error !!!"+e.getMessage());
		}finally{
			if(conn != null) conn.close();
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		}
	 
	 
	 return reduce;
 }


























}
