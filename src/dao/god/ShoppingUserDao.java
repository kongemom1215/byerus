package dao.god;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ShoppingUserDao {
	private static ShoppingUserDao instance;
	
	private ShoppingUserDao() {
		
	}
	
	public static ShoppingUserDao getInstance() {
		if(instance==null)
			instance= new ShoppingUserDao();
		return instance;
	}
	private Connection getConnection() {
		Connection conn=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn=ds.getConnection();
		} catch (Exception e) {
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
				shoppinguser.setSpost(rs.getInt(9));
				shoppinguser.setSpwd(rs.getString(4));
				shoppinguser.setSregdate(rs.getDate(7));
				shoppinguser.setStype(rs.getInt(2));
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
	public int getTotalUser() throws SQLException {
		int totalUser=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from ShoppingUser";
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalUser=rs.getInt(1)-1;
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
		return totalUser;
	}

	public List<ShoppingUser> list(int startRow, int endRow) throws SQLException {
		String sql="select * from (select rownum rn, s.* from (select * from ShoppingUser order by sid ) s) where rn between ? and ?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ShoppingUser> userlist=new ArrayList<ShoppingUser>();
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow+1);
			pstmt.setInt(2, endRow+1);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ShoppingUser shoppinguser=new ShoppingUser();
				shoppinguser.setSid(rs.getInt(2));
				shoppinguser.setStype(rs.getInt(3));
				shoppinguser.setSemail(rs.getString(4));
				shoppinguser.setSpwd(rs.getString(5));
				shoppinguser.setSname(rs.getString(6));
				shoppinguser.setScontact(rs.getString(7));
				shoppinguser.setSregdate(rs.getDate(8));
				shoppinguser.setSaddress(rs.getString(9));
				shoppinguser.setSpost(rs.getInt(10));
				shoppinguser.setSagree(rs.getString(11));
				
				userlist.add(shoppinguser);
			}
		} catch (Exception e) {
			System.out.println("list 메소드 -> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return userlist;
	}

	public int updateUser(ShoppingUser shoppinguser, int sid) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		//이름,이메일,연락처,우편번호,주소,마켓팅동의여부
		String sql="update ShoppingUser set sname=?, scontact=?, spost=?, saddress=?, sagree=? where sid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, shoppinguser.getSname());
			pstmt.setString(2, shoppinguser.getScontact());
			pstmt.setInt(3, shoppinguser.getSpost());
			pstmt.setString(4, shoppinguser.getSaddress());
			pstmt.setString(5, shoppinguser.getSagree());
			pstmt.setInt(6, sid);
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateUser() ->"+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return result;
	}

	public int deleteUser(int sid) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		//자식->부모 순으로 삭제
		String sql1="delete from order_tb where sid=?";
		String sql2="delete from ShoppingUser where sid=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql1);
			pstmt.setInt(1, sid);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, sid);
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteUser() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}

	public int idCheck(String semail) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select * from ShoppingUser where semail=?";
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, semail);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=0;
			}
			else
				result=1;
			
		} catch (Exception e) {
			System.out.println("idCheck() -> "+e.getMessage());
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

	public int insertUser(ShoppingUser shoppinguser) throws SQLException {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="insert into ShoppingUser values(?,1,?,?,?,?,sysdate,?,?,?,?,?)";
		String sql2="select nvl(max(sid)+1,0) from ShoppingUser";
		int max=9999;
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			if(rs.next())
				max=rs.getInt(1);
			rs.close();
			pstmt.close();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, max);
			pstmt.setString(2, shoppinguser.getSemail());
			pstmt.setString(3, shoppinguser.getSpwd());
			pstmt.setString(4, shoppinguser.getSname());
			pstmt.setString(5, shoppinguser.getScontact());
			pstmt.setString(6, shoppinguser.getSaddress());
			pstmt.setInt(7, shoppinguser.getSpost());
			pstmt.setString(8, shoppinguser.getSagree());
			pstmt.setString(9, shoppinguser.getSquestion());
			pstmt.setString(10, shoppinguser.getSanswer());
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertUser() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return result;
	}

	public List<ShoppingUser> searchlist(String option, String search_value, int startRow, int endRow) throws SQLException {
		List<ShoppingUser> userlist=new ArrayList<ShoppingUser>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = null;
		
		if(option.equals("sid")) {
			sql="select * from (select rownum rn, s.* from (select * from shoppingUser where sid like '%"+search_value+"%' and stype=1 order by sid ) s) where rn between ? and ?";
		}
		if(option.equals("sname")) {
			sql="select * from (select rownum rn, s.* from (select * from shoppingUser where sname like '%"+search_value+"%' and stype=1 order by sid ) s) where rn between ? and ?";
		}
		if(option.equals("semail")) {
			sql="select * from (select rownum rn, s.* from (select * from shoppingUser where semail like '%"+search_value+"%' and stype=1 order by sid ) s) where rn between ? and ?";
		}
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			//pstmt.setString(1, search_value);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ShoppingUser shoppinguser=new ShoppingUser();
				shoppinguser.setSid(rs.getInt(2));
				shoppinguser.setStype(rs.getInt(3));
				shoppinguser.setSemail(rs.getString(4));
				shoppinguser.setSpwd(rs.getString(5));
				shoppinguser.setSname(rs.getString(6));
				shoppinguser.setScontact(rs.getString(7));
				shoppinguser.setSregdate(rs.getDate(8));
				shoppinguser.setSaddress(rs.getString(9));
				shoppinguser.setSpost(rs.getInt(10));
				shoppinguser.setSagree(rs.getString(11));
				
				userlist.add(shoppinguser);
			}
		} catch (Exception e) {
			System.out.println("serachlist() -> "+e.getMessage());
		} finally {
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return userlist;
	}

	public int searchtotal(String option, String search_value) throws SQLException {
		int totalUser=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = null;
		
		if(option.equals("sid")) {
			sql="select count(*) from (select s.* from (select * from shoppingUser where sid like '%"+search_value+"%' and stype=1 order by sid ) s)";
		}
		if(option.equals("sname")) {
			sql="select count(*) from (select s.* from (select * from shoppingUser where sname like '%"+search_value+"%' and stype=1 order by sid ) s)";
		}
		if(option.equals("semail")) {
			sql="select count(*) from (select s.* from (select * from shoppingUser where semail like '%"+search_value+"%' and stype=1 order by sid ) s)";
		}
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalUser=rs.getInt(1);
		} catch (Exception e) {
			System.out.println("searchtotal()-> "+e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		
		return totalUser;
	}

}
