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

import dao.god.Product;

public class ProductDao {
	private static ProductDao instance;
	private ProductDao() {}
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
			DataSource ds = (DataSource)
				ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		}catch(Exception e) { 
			System.out.println(e.getMessage());	
			}
		return conn;
	}
	
	public List<Product> main_img() throws SQLException {
		List<Product> list = new ArrayList<Product>();
		Connection conn = null;	
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		String sql = "select * from (select rownum, a.* from (SELECT * from product a order by psell desc) a) where rownum <= 6";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
				Product su = new Product();
				su.setPid(rs.getInt("pid"));
				su.setPtype(rs.getString("ptype"));
				su.setCol1(rs.getString("col1"));
				su.setCol2(rs.getString("col2"));
				su.setCol3(rs.getString("col3"));
				su.setPprice(rs.getInt("pprice"));
				su.setPname(rs.getString("pname"));
				su.setPregdate(rs.getDate("pregdate"));
				su.setPsell(rs.getInt("psell"));
				su.setPdiscount(rs.getInt("pdiscount"));
				su.setPpublic(rs.getInt("ppublic"));
				su.setPthumbimg(rs.getString("pthumbimg"));
				su.setPoption(rs.getString("poption"));
				list.add(su);
			} while (rs.next());
			}
		} catch (Exception e) {
			
		} finally {
				if (rs !=null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn !=null) conn.close();
			} 
		
		return list;
	
	}
	public int getTotalProduct() throws SQLException {
		int totalProduct=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from Product";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalProduct=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("getTotalProduct() -> "+e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return totalProduct;
	}
	public List<Product> list(int startRow, int endRow) throws SQLException {
		List<Product> productlist=new ArrayList<Product>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from (select rownum rn, p.* from (select * from Product order by pid) p) where rn between ? and ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product=new Product();
				product.setPid(rs.getInt(2));
				product.setPtype(rs.getString(3));
				product.setCol1(rs.getString(4));
				product.setCol2(rs.getString(5));
				product.setCol3(rs.getString(6));
				product.setPprice(rs.getInt(7));
				product.setPinventory(rs.getInt(8));
				product.setPname(rs.getString(9));
				product.setPregdate(rs.getDate(10));
				product.setPsell(rs.getInt(11));
				product.setPdiscount(rs.getInt(12));
				product.setPpublic(rs.getInt(13));
				product.setPthumbimg(rs.getString(14));
				product.setPoption(rs.getString(15));
				
				productlist.add(product);
			}
		} catch (Exception e) {
			System.out.println("product list() -> "+e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		return productlist;
	}
	
	public Product select(int pid) throws SQLException {
		Product product=new Product();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from Product where pid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				product.setPid(pid);
				product.setPtype(rs.getString(2));
				product.setCol1(rs.getString(3));
				product.setCol2(rs.getString(4));
				product.setCol3(rs.getString(5));
				product.setPprice(rs.getInt(6));
				product.setPinventory(rs.getInt(7));
				product.setPname(rs.getString(8));
				product.setPregdate(rs.getDate(9));
				product.setPsell(rs.getInt(10));
				product.setPdiscount(rs.getInt(11));
				product.setPpublic(rs.getInt(12));
				product.setPthumbimg(rs.getString(13));
				product.setPoption(rs.getString(14));
			}
		} catch (Exception e) {
			System.out.println("product select() -> "+e.getMessage());
		} finally {
			if (rs !=null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn !=null) conn.close();
		}
		
		return product;
	}
	public int updateProduct(Product product) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update Product set pname=?, ptype=?, pprice=?, pinventory=?, pdiscount=?, ppublic=?, pthumbimg=?, col1=?, col2=?, col3=?, poption=? where pid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getPtype());
			pstmt.setInt(3, product.getPprice());
			pstmt.setInt(4, product.getPinventory());
			pstmt.setInt(5, product.getPdiscount());
			pstmt.setInt(6, product.getPpublic());
			pstmt.setString(7, product.getPthumbimg());
			pstmt.setString(8, product.getCol1());
			pstmt.setString(9, product.getCol2());
			pstmt.setString(10, product.getCol3());
			pstmt.setString(11, product.getPoption());
			pstmt.setInt(12, product.getPid());
			
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updatePd() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	public int deleteProduct(int pid) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from Product where pid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteProduct() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	
	public int insert(Product product) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="insert into Product values(?,?,?,?,?,?,?,?,sysdate,0,?,?,?,?)";
		String sql2="select nvl(max(pid),0) from product";
		int pid=0;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			if(rs.next())
				pid=rs.getInt(1)+1;
			rs.close();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setString(2, product.getPtype());
			pstmt.setString(3, product.getCol1());
			pstmt.setString(4, product.getCol2());
			pstmt.setString(5, product.getCol3());
			pstmt.setInt(6, product.getPprice());
			pstmt.setInt(7, product.getPinventory());
			pstmt.setString(8, product.getPname());
			pstmt.setInt(9, product.getPdiscount());
			pstmt.setInt(10, product.getPpublic());
			pstmt.setString(11, product.getPthumbimg());
			pstmt.setString(12, product.getPoption());
			
			result=pstmt.executeUpdate();
			System.out.println("result : "+result);
		} catch (Exception e) {
			System.out.println("product insert() ->"+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}
	public List<Product> searchlist(String option, String search_value, int startRow, int endRow) throws SQLException {
		List<Product> productlist=new ArrayList<Product>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = null;
		
		if(option.equals("pid")) {
			sql="select * from (select rownum rn, p.* from (select * from product where pid like '%"+search_value+"%' order by pid ) p) where rn between ? and ?";
		}
		if(option.equals("pname")) {
			sql="select * from (select rownum rn, p.* from (select * from product where pname like '%"+search_value+"%' order by pid ) p) where rn between ? and ?";
		}
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Product product=new Product();
				product.setPid(rs.getInt(2));
				product.setPtype(rs.getString(3));
				product.setCol1(rs.getString(4));
				product.setCol2(rs.getString(5));
				product.setCol3(rs.getString(6));
				product.setPprice(rs.getInt(7));
				product.setPinventory(rs.getInt(8));
				product.setPname(rs.getString(9));
				product.setPregdate(rs.getDate(10));
				product.setPsell(rs.getInt(11));
				product.setPdiscount(rs.getInt(12));
				product.setPpublic(rs.getInt(13));
				product.setPthumbimg(rs.getString(14));
				product.setPoption(rs.getString(15));
				
				productlist.add(product);
			}
		} catch (Exception e) {
			System.out.println("product serachlist() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return productlist;
	}
	public int searchtotal(String option, String search_value) throws SQLException {
		int totalProduct=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = null;
		
		if(option.equals("pid")) {
			sql="select count(*) from (select p.* from (select * from Product where pid like '%"+search_value+"%' order by pid ) p)";
		}
		if(option.equals("pname")) {
			sql="select count(*) from (select p.* from (select * from Product where pname like '%"+search_value+"%' order by pid ) p)";
		}
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalProduct=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("product searchtotal()-> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return totalProduct;
	}
}
