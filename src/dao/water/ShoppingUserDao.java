package dao.water;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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

public class ShoppingUserDao {
	
	private static ShoppingUserDao instance;
	private ShoppingUserDao() {}
	public static ShoppingUserDao getInstance() {
		if (instance == null) {	
			instance = new ShoppingUserDao();		
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
	
	public int login(String semail, String spwd) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select spwd from ShoppingUser where semail=?";
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, semail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				if(spwd.equals(rs.getString(1)))
					result=1;
				else
					result=0; //비밀번호가 틀릴때
			}
			else
				result=-1; //아이디 비번 둘 다 없을 때 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	public ShoppingUser select(String semail) throws SQLException {
		String sql="select * from ShoppingUser where semail=?";
		ShoppingUser shoppinguser = new ShoppingUser();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, semail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				shoppinguser.setSemail(rs.getString(3));
				shoppinguser.setSaddress(rs.getString(8));
				shoppinguser.setSagree(rs.getString(10));
				shoppinguser.setScontact(rs.getString(6));
				shoppinguser.setSname(rs.getString(5));
				shoppinguser.setSid(rs.getInt(1));
				shoppinguser.setSpost(rs.getInt("spost"));
				shoppinguser.setSpwd(rs.getString("spwd"));
				shoppinguser.setSregdate(rs.getDate("sregdate"));
				shoppinguser.setStype(rs.getInt("stype"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return shoppinguser;
	}
	
	public ShoppingUser select(int sid) throws SQLException {
		String sql="select * from ShoppingUser where sid=?";
		ShoppingUser shoppinguser = new ShoppingUser();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, sid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				shoppinguser.setSemail(rs.getString(3));
				shoppinguser.setSaddress(rs.getString(8));
				shoppinguser.setSagree(rs.getString(10));
				shoppinguser.setScontact(rs.getString(6));
				shoppinguser.setSname(rs.getString(5));
				shoppinguser.setSid(rs.getInt(1));
				shoppinguser.setSpost(rs.getInt(9));
				shoppinguser.setSpwd(rs.getString(4));
				shoppinguser.setSregdate(rs.getDate(7));
				shoppinguser.setStype(rs.getInt(2));
				shoppinguser.setSquestion(rs.getString(11));
				shoppinguser.setSanswer(rs.getString(12));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return shoppinguser;
	}
	
	public int insert(ShoppingUser user) throws SQLException {
		int sid = user.getSid();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		ResultSet rs = null;
		String sql1 = "select nvl(max(sid),0) from shoppinguser";
		String sql="insert into shoppinguser values(?,?,?,?,?,?,sysdate,?,?,?,?,?)";

		try { 
			conn  = getConnection();

			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			rs.next();
			// 일단 max사용
			int number = rs.getInt(1) + 1;
			rs.close();
			pstmt.close();
			if (sid == 0 ) user.setSid(number);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, number);
			pstmt.setInt(2, user.getStype());
			pstmt.setString(3, user.getSemail());
			pstmt.setString(4, user.getSpwd());
			pstmt.setString(5, user.getSname());
			pstmt.setString(6, user.getScontact());
			pstmt.setString(7, user.getSaddress());
			pstmt.setInt(8, user.getSpost());
			pstmt.setString(9, user.getSagree());
			pstmt.setString(10, user.getSquestion());
			pstmt.setString(11, user.getSanswer());
			result = pstmt.executeUpdate();
		} catch(Exception e) { System.out.println(e.getMessage());
		} finally {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		}
		return result;
	}
	
	
	public ShoppingUser findem(String sname, String squestion, String sanswer) throws SQLException {
		ShoppingUser shoppinguser = new ShoppingUser();
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select * from ShoppingUser where sname=? and squestion=? and sanswer=?";
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, sname);
			pstmt.setString(2, squestion);
			pstmt.setString(3, sanswer);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				shoppinguser.setSemail(rs.getString(3));
				shoppinguser.setSaddress(rs.getString(8));
				shoppinguser.setSagree(rs.getString(10));
				shoppinguser.setScontact(rs.getString(6));
				shoppinguser.setSname(rs.getString(5));
				shoppinguser.setSid(rs.getInt(1));
				shoppinguser.setSpost(rs.getInt(9));
				shoppinguser.setSpwd(rs.getString(4));
				shoppinguser.setSregdate(rs.getDate(7));
				shoppinguser.setStype(rs.getInt(2));
				shoppinguser.setSquestion(rs.getString(11));
				shoppinguser.setSanswer(rs.getString(12));
			}
		} catch (Exception e) {
			System.out.println("findem->"+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return shoppinguser;
	}
	
	
	public ShoppingUser findpwd(String semail,String sname,String squestion, String sanswer) throws SQLException {
		ShoppingUser shoppinguser = new ShoppingUser();
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select * from ShoppingUser where semail=? and sname=? and squestion=? and sanswer=?";
		ResultSet rs=null;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, semail);
			pstmt.setString(2, sname);
			pstmt.setString(3, squestion);
			pstmt.setString(4, sanswer);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				shoppinguser.setSemail(rs.getString(3));
				shoppinguser.setSaddress(rs.getString(8));
				shoppinguser.setSagree(rs.getString(10));
				shoppinguser.setScontact(rs.getString(6));
				shoppinguser.setSname(rs.getString(5));
				shoppinguser.setSid(rs.getInt(1));
				shoppinguser.setSpost(rs.getInt(9));
				shoppinguser.setSpwd(rs.getString(4));
				shoppinguser.setSregdate(rs.getDate(7));
				shoppinguser.setStype(rs.getInt(2));
				shoppinguser.setSquestion(rs.getString(11));
				shoppinguser.setSanswer(rs.getString(12));
				
			}
		} catch (Exception e) {
			System.out.println("findpwd->"+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return shoppinguser;
	}
	

	
}
