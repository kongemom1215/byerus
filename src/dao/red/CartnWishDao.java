package dao.red;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.e;

public class CartnWishDao {
	private static CartnWishDao instance;
	private CartnWishDao() {}
	
	public static CartnWishDao getInstance() {
		if(instance == null) {
			instance = new CartnWishDao();
		}
		return instance;
	}
	public Connection getConnection() {
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
//장바구니에 담긴 상품 불러오기	
	public ArrayList<CartnWish> select(int session_sid) throws SQLException {
		
		ArrayList<CartnWish> list = new ArrayList<CartnWish>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql=
				"Select p.pid, p.pname,p.pprice,p.PDISCOUNT, c.cwid, c.cwqty, s.sid, c.cwoption\r\n"
				+ "From product p , cartnwish c , SHOPPINGUSER s\r\n"
				+ "where p.pid = c.pid\r\n"
				+ "And c.sid = s.sid\r\n"
				+ "ANd c.sid=? \r\n"
				+ "order by cwid";
		try {
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, session_sid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					CartnWish cart = new CartnWish();
					cart.setPid(rs.getInt("pid"));
					cart.setPname(rs.getString("pname"));
					cart.setPprice(rs.getInt("pprice"));
					cart.setCwid(rs.getInt("cwid"));
					cart.setCwqty(rs.getInt("cwqty"));
					cart.setSid(rs.getInt("sid"));
					cart.setCwoption(rs.getString("cwoption"));
					cart.setPdiscount(rs.getInt("pdiscount"));
					list.add(cart);
					
				}while(rs.next());	
					
				
			}
			
			
		} catch (Exception e) {
			System.out.println("CartnWishDao select error !!"+e.getMessage());
		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			if(rs != null) rs.close();
		}
		
		return list;
	}
	
	//선택된 값 장바구니 테이블에 insert
		public int insert (int pid,int session_sid,int howmany,String option) throws SQLException{
			
			int result=0;
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
			System.out.println("insert1 ");
			
			String sql1 = "select nvl(max(cwid),0) from cartnwish";
			String sql2 = "select count(*) from cartnwish where  pid=? and sid=? and CWTYPE='cart' and cwoption=? ";
			String sql3 = "Insert into cartnwish values(?,?,?,?,?,?)";
			String sql4 = "update cartnwish set cwqty=cwqty+? where sid=? and pid=? and CWTYPE='cart' and cwoption=?";
			System.out.println("insert2 ");		
			System.out.println("CartnWishDao insert pid->"+pid);		
			System.out.println("CartnWishDao insert session_sid->"+session_sid);		
			System.out.println("CartnWishDao insert option->"+option);		
			System.out.println("CartnWishDao insert howmany->"+howmany);		
				
			
			try {
				conn=getConnection();
				  
				 pstmt=conn.prepareStatement(sql2);
				 pstmt.setInt(1, pid );
				 pstmt.setInt(2,session_sid);
				 pstmt.setString(3, option);
				 rs=pstmt.executeQuery(); 
				 System.out.println("insert3 ");
				 if(rs.next()) {
					 int count=rs.getInt(1);
					System.out.println("CartnWishDao insert count->"+count);
					if(count == 0) {
						  //장바구니에 담긴 상품이 없으면 insert
						 pstmt.close();
						 rs.close();
						 pstmt=conn.prepareStatement(sql1);
						 rs=pstmt.executeQuery();
						 rs.next();
						 int num=rs.getInt(1)+1;
						 pstmt.close();
						 rs.close();
						 System.out.println("cwid =>"+num);
						 System.out.println("insert4 ");
						 
						 pstmt=conn.prepareStatement(sql3);
						 pstmt.setInt(1, num);//카트위시번호
						 pstmt.setInt(2, session_sid); //유저번호 가져오기
						 pstmt.setInt(3, pid); //상품번호 가져오기
						 pstmt.setString(4, "cart");
						 pstmt.setInt(5,howmany); 
						 pstmt.setString(6, option);
						 rs=pstmt.executeQuery();
						 result=0;
//						 result=pstmt.executeUpdate();
						 
					 }else {
						  //장바구니에 담긴 상품이 있으면 update
					  pstmt.close();
					  rs.close();
					  System.out.println("insert5 ");
					  pstmt=conn.prepareStatement(sql4); 
					  pstmt.setInt(1, howmany);
					  pstmt.setInt(2, session_sid);
					  pstmt.setInt(3, pid);
					  pstmt.setString(4, option);
					  rs=pstmt.executeQuery();
					  result=1;
//					  result=pstmt.executeUpdate();
					  
					 }
					
				  }else {
					  result=-1;
				  }
					
				
				System.out.println("result 값은??=>"+result);
				
			}catch(Exception e) {
				System.out.println("CartnWhisDao insert error!!"+e.getMessage());
			}finally {
				if(conn !=null) conn.close();
				if(pstmt !=null) pstmt.close();
				if(rs != null)rs.close();
				
			}
			
			return result;
		}
//전체 장바구니 상품 지우기	
	public int delete(int session_sid) throws SQLException {
		int result=0;
		Connection conn= null;
		PreparedStatement pstmt=null;
		
		String sql="delete from cartnwish where sid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,session_sid);
			result=pstmt.executeUpdate();
			System.out.println("======================");
			System.out.println("result 값은=>"+result );
			
			
			
		}catch(Exception e) {
			System.out.println("CartnWishDao delete error"+e.getMessage());
		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			
		}
		
		return result;
	}
//장바구니에서 전체 상품 개수	
	public int count(int session_sid) throws SQLException {
		int count =0;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select sum(cwqty) from cartnwish where sid = ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, session_sid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("======================");
			System.out.println("장바구니 상품 총 개수 =>"+count);
			
		}catch(Exception e) {
			System.out.println("CartnWishDao count error!!"+e.getMessage());
		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			if(rs != null) rs.close();
		}
		return count;
		
	}
//장바구니에서 개별삭제
	public int CheckDelete(CartnWish cart) throws SQLException {
		int result = 0;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from cartnwish where cwid=?";
		
		try {
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getCwid());
			System.out.println("cart.getCwid()==>"+ cart.getCwid());
			result=pstmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			System.out.println("CartnWish CheckDelete error!! "+ e.getMessage());
		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			if(rs != null) rs.close();
		}
		
		return result;
	}
	
//개별주문
	public ArrayList<CartnWish> checkSelect(int check_cwid) throws SQLException{
		ArrayList<CartnWish> list = new ArrayList<CartnWish>();
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select p.pid,p.pname,p.pdiscount,p.pprice,c.cwqty,c.cwoption"
				  + " from cartnwish c ,product p "
					+ "where p.pid = c.pid\r\n"
					+ "ANd c.cwid=? \r\n";
		
		try {
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,check_cwid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					CartnWish cart = new CartnWish();
					cart.setPid(rs.getInt("pid"));
					cart.setPname(rs.getString("pname"));
					cart.setPdiscount(rs.getInt("pdiscount"));
					cart.setPprice(rs.getInt("pprice"));
					cart.setCwqty(rs.getInt("cwqty"));
					cart.setCwoption(rs.getString("cwoption"));
					list.add(cart);
					
				}while(rs.next());	
					
				System.out.println("list==>"+list);
			}

		}catch(Exception e) {
			System.out.println("CartnwishDao checkSelect error!! "+e.getMessage());

		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			if(rs != null) rs.close();
		}

		
		return list;
	}
//개별주문의 상품 개수
	public int countCheck(int check_cwid) throws SQLException {
		int count =0;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select sum(cwqty) from cartnwish where cwid = ?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,check_cwid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
			System.out.println("======================");
			System.out.println("선택한 상품 총 개수 =>"+count);
			
		}catch(Exception e) {
			System.out.println("CartnWishDao countCheck error!!"+e.getMessage());
		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			if(rs != null) rs.close();
		}
		return count;
		
	}

//선택상품의 총액구하기
	public int sum(CartnWish cart) throws SQLException {
		int sum = 0;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select (p.pprice*c.cwqty)\n"
				+ "from cartnwish c , product p\n"
				+ "where c.pid = p.pid\n"
				+ "and c.cwid = ?";
		System.out.println("확인1");		

		
		try {
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getCwid());
			rs=pstmt.executeQuery();
			System.out.println("확인2");
			while(rs.next()) {
				sum=rs.getInt(1);
				System.out.println("확인3");
			}
		}catch(Exception e) {
			System.out.println("CartnWishDao sum error!!"+e.getMessage());
		}finally {
			if(conn != null) conn.close();
			if(pstmt != null)pstmt.close();
			if(rs != null) rs.close();
		}
		
		
		return sum;
	}
//장바구니에 담긴 전체 상품pid 가져오기

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
