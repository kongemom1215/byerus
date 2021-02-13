package dao.red;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ShoppingUserDao {
	private static ShoppingUserDao instance;
	private ShoppingUserDao() {}
	public static ShoppingUserDao getInstance() {
		if(instance == null) {
			instance = new ShoppingUserDao();
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
	public int check(String useremail, String password) throws SQLException {
		int result =0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		String sql="select spwd from shoppingUser where semail=? ";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, useremail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String dbpass = rs.getString(1);
				if(dbpass.equals(password)) {
					result=1;
				}else {
					result=0;
				}
			}else {
				result=-1;
			}
			
			
		}catch(Exception e) {
			System.out.println("shoppingUserDao Error!!"+e. getMessage());
		}finally {
			if(conn !=null) conn.close();
			if(pstmt !=null) pstmt.close();
			if(rs !=null) rs.close();
		}
		
		
		return result;
	}
	
	public int selectSid(String useremail) throws SQLException {
		int sid=0;
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="select sid from shoppingUser where SEMAIL=?";
		
		try {
			
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, useremail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				sid=rs.getInt(1);
			}
			
			
		}catch(Exception e) {
			System.out.println("shoppingUserDao selectSid error!!"+e.getMessage());
		}finally {
			if(conn !=null) conn.close();
			if(pstmt !=null) pstmt.close();
			if(rs !=null) rs.close();
		}
		
		return sid;
	}
	public ShoppingUser select(int sid) throws SQLException {
		ShoppingUser user= new ShoppingUser();
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sname,saddress,spost,scontact,semail from shoppinguser where sid=?";
		
		
		try {
			
			conn=getConnection();
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				user.setSname(rs.getString("sname"));
				user.setSaddress(rs.getString("saddress"));
				user.setSpost(rs.getInt("spost"));
				user.setScontact(rs.getString("scontact"));
				user.setSemail(rs.getString("semail"));
				
			}
			
			
		}catch(Exception e) {
			System.out.println("ShoppingUserDao error!!"+e.getMessage());
		}finally {
			if(conn !=null) conn.close();
			if(pstmt !=null) pstmt.close();
			if(rs !=null) rs.close();
		}
		
		return user;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

